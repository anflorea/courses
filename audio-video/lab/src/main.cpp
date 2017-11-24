#include "ppmEncoder.h"
#include "FileWorker.h"
#include "RgbPixel.h"
#include "Block88.h"

char output[30] = "output.ppm";

std::vector<Block88> luma;
std::vector<Block88> chRed;
std::vector<Block88> chBlue;

int N;
int M;

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
	}
	
	for (auto &block: chRed) {
		block.subtract128();
		block.forwardDCT();
		block.quantize();
	}

	for (auto &block: chBlue) {
		block.subtract128();
		block.forwardDCT();
		block.quantize();
	}

}

void decodeImage(char *fileName) {
	printf("Should decode the image: %s\n", fileName);
	
	for (auto &block: luma) {
		block.deQuantize();
		block.inverseDCT();
		block.add128();
	}
	
	for (auto &block: chRed) {
		block.deQuantize();
		block.inverseDCT();
		block.add128();
	}

	for (auto &block: chBlue) {
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
