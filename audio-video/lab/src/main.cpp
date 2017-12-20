#include "ppmEncoder.h"
#include "FileWorker.h"
#include "RgbPixel.h"
#include "Block88.h"

char output[30] = "output.ppm";

std::vector<Block88> luma;
std::vector<Block88> chRed;
std::vector<Block88> chBlue;

std::vector<int> encoded;

int N;
int M;

// #define DEBUG

void encodeImage(char *fileName) {
	printf("Should encode the image: %s\n", fileName);

	FileWorker *fw = new FileWorker(fileName, output);
	fw->readPpm();

	N = fw->getHeight();
	M = fw->getWidth();

	RgbPixel **picture = fw->getPhoto();

	for (int i = 0; i < N; i += 8) {
		for (int j = 0; j < M; j += 8) {
			luma.push_back(Block88(picture, i, j, 'Y'));
			chRed.push_back(Block88(picture, i, j, 'U'));
			chBlue.push_back(Block88(picture, i, j, 'V'));
		}
	}

	for (auto &block: chRed) {
		block.subSample();
		block.upSample();
	}

	for (auto &block: chBlue) {
		block.subSample();
		block.upSample();
	}

	for (auto &block: luma) {
		block.subtract128();
		block.forwardDCT();
		block.quantize();
		block.toCoefArray();
		block.toDCCoefs();
	}
	
	for (auto &block: chRed) {
		block.subtract128();
		block.forwardDCT();
		block.quantize();
		block.toCoefArray();
		block.toDCCoefs();
	}


	for (auto &block: chBlue) {
		block.subtract128();
		block.forwardDCT();
		block.quantize();
		block.toCoefArray();
		block.toDCCoefs();
	}

	for (int i = 0; i < luma.size(); i++) {
		luma.at(i).toDCCoefs();
		std::vector<int> theLumas = luma.at(i).toByteArray();
		for (auto &e: theLumas)
			encoded.push_back(e);
		chRed.at(i).toDCCoefs();
		std::vector<int> theChReds = chRed.at(i).toByteArray();
		for (auto &e: theChReds)
			encoded.push_back(e);
		chBlue.at(i).toDCCoefs();
		std::vector<int> theChBlues = chBlue.at(i).toByteArray();
		for (auto &e: theChBlues)
			encoded.push_back(e);
	}

#ifdef DEBUG
	luma.at(500).printBlock();
	luma.at(500).toCoefArray();
	for (int i = 0; i < 64; i++)
		printf("%d ", luma.at(500).coefArray[i]);
	printf("\n\n");

	luma.at(500).dcCoef.clear();
	luma.at(500).toDCCoefs();

	printf("(%d)(%d),", std::get<0>(luma.at(500).firstDC), std::get<1>(luma.at(500).firstDC));
	for (auto &el: luma.at(500).dcCoef) {
		int zeros = std::get<0>(el);
		int size = std::get<1>(el);
		int amp = std::get<2>(el);
		printf("(%d,%d)(%d),", zeros, size, amp);
	}
	if (luma.at(500).endsIn0)
		printf("(0,0)\n\n");

	std::vector<int> bytes = luma.at(500).toByteArray();
	for (auto &el: bytes) {
		printf("%d ", el);
	}

	printf("\n\n");

	Block88 testBlock = Block88(bytes, luma.at(500).getPositionX(), luma.at(500).getPositionY());

	printf("(%d)(%d),", std::get<0>(testBlock.firstDC), std::get<1>(testBlock.firstDC));
	for (auto &el: testBlock.dcCoef) {
		int zeros = std::get<0>(el);
		int size = std::get<1>(el);
		int amp = std::get<2>(el);
		printf("(%d,%d)(%d),", zeros, size, amp);
	}
	if (testBlock.endsIn0)
		printf("(0,0)\n\n");

	printf("\n\n");

	testBlock.fromDCCoefs();
	testBlock.fromCoefArray();

	testBlock.printBlock();

	luma.at(500).fromDCCoefs();

	luma.at(500).fromCoefArray();
	luma.at(500).printBlock();
#endif // DEBUG
}

std::vector<int> getOneByteArray() {
	std::vector<int> one;
	int a;
	int b;
	int c;
	int	t = 1;

	// DC Coeficients
	one.push_back(encoded.front());
	encoded.erase(encoded.begin());
	one.push_back(encoded.front());
	encoded.erase(encoded.begin());

	// Other values
	while (t < 64) {
		a = encoded.front();
		encoded.erase(encoded.begin());
		one.push_back(a);
		t += a;
		b = encoded.front();
		encoded.erase(encoded.begin());
		one.push_back(b);
		if (a != 0 || b != 0) {
			c = encoded.front();
			encoded.erase(encoded.begin());
			one.push_back(c);
		}
		else
			return one;
		t++;
	}
	return one;
}

void decodeImage(char *fileName) {
	printf("Should decode the image: %s\n", fileName);

	std::vector<Block88> newLuma = luma;
	std::vector<Block88> newChRed = chRed;
	std::vector<Block88> newChBlue = chBlue;

	luma = std::vector<Block88>();
	chRed = std::vector<Block88>();
	chBlue = std::vector<Block88>();

	int	i = 0;
	int j = 0;

	while (encoded.size() > 0) {
		std::vector<int> theLumas = getOneByteArray();
		luma.push_back(Block88(theLumas, i, j));
		std::vector<int> theChReds = getOneByteArray();
		chRed.push_back(Block88(theChReds, i, j));
		std::vector<int> theChBlues = getOneByteArray();
		chBlue.push_back(Block88(theChBlues, i, j));
		j += 8;
		if (j >= M) {
			i += 8;
			j = 0;
		}
	}


	for (auto &block: luma) {
		block.fromDCCoefs();
	}

	for (auto &block: chRed) {
		block.fromDCCoefs();
	}

	for (auto &block: chBlue) {
		block.fromDCCoefs();
	}

	for (auto &block: luma) {
		block.fromCoefArray();
		block.deQuantize();
		block.inverseDCT();
		block.add128();
	}
	
	for (auto &block: chRed) {
		block.fromCoefArray();
		block.deQuantize();
		block.inverseDCT();
		block.add128();
	}

	for (auto &block: chBlue) {
		block.fromCoefArray();
		block.deQuantize();
		block.inverseDCT();
		block.add128();
	}

	FileWorker *fw = new FileWorker(NULL, fileName);
	fw->createPhoto(N, M);

	for (int t = 0; t < luma.size(); t++) {
		int x = luma.at(t).getPositionX();
		int y = luma.at(t).getPositionY();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int Y = luma.at(t).m_values[i][j];
				int U = chRed.at(t).m_values[i][j];
				int V = chBlue.at(t).m_values[i][j];

				RgbPixel pixel;
				pixel.fromYUV(Y, U, V);

				fw->putPixel(x + i, y + j, pixel);
			}
		}
	}

	fw->writePpm();
}

void testPrintYUV() {
	printf("\n\nShould print the values in luma, chRed, chBlue\n\n");
	
/*
	for (auto &block: chRed) {
		block.printBlock();
	}
*/
}

int main(int argc, char **argv) {
	if (argc != 3)
		printUsage();
	if (strcmp(argv[1], "encode") != 0 && strcmp(argv[1], "decode") != 0 && strcmp(argv[1], "test"))
		printUsage();
	if (!checkFile(argv[2]))
		printUsage();
	if (strcmp(argv[1], "encode") == 0)
		encodeImage(argv[2]);
	else if (strcmp(argv[1], "decode") == 0)
		decodeImage(argv[2]);
	else {
		encodeImage(argv[2]);
		testPrintYUV();
		decodeImage(output);
	}
	return 0;
}
