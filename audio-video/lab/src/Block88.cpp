#include "Block88.h"

#include "RgbPixel.h"
#include <math.h>

int const Block88::Q[8][8] =
	{{6, 4, 4, 6, 10, 16, 20, 24},
	{5, 5, 6, 8, 10, 23, 24, 22},
	{6, 5, 6, 10, 16, 23, 28, 22},
	{6, 7, 9, 12, 20, 35, 32, 25},
	{7, 9, 15, 22, 27, 44, 41, 31},
	{10, 14, 22, 26, 32, 42, 45, 37},
	{20, 26, 31, 35, 41, 48, 48, 40},
	{29, 37, 38, 39, 45, 40, 41, 40}};

Block88::Block88(RgbPixel **matrix, int x, int y, char component) {
	m_positionX = x;
	m_positionY = y;
	for (int i = x; i < x + 8; i++) {
		for (int j = y; j < y + 8; j++) {
			switch (component) {
				case 'Y':
					m_values[i - x][j - y] = matrix[i][j].getY();
					break;
				case 'U':
					m_values[i - x][j - y] = matrix[i][j].getU();
					break;
				case 'V':
					m_values[i - x][j - y] = matrix[i][j].getV();
					break;
			}
		}
	}
}

void Block88::subSample() {
	int i, j, x, y;

	x = 0;
	for (i = 0; i < 8; i += 2) {
		y = 0;
		for (j = 0; j < 8; j += 2) {
			m_subValues[x][y] =
				(m_values[i][j] +
				m_values[i + 1][j] +
				m_values[i][j + 1] +
				m_values[i + 1][j + 1]) / 4;
				
			y++;
		}
		x++;
	}
}

void Block88::upSample() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			m_values[i][j] = m_subValues[i / 2][j / 2];
		}
	}
}

void Block88::printBlock() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			printf("%3d ", m_values[i][j]);
		}
		if (i < 4) {
			printf("    ");
			for (int j = 0; j < 4; j++) {
				printf("%3d ", m_subValues[i][j]);
			}
		}
		printf("\n");
	}
	printf("\n");
}

void Block88::subtract128() {
	for (int i = 0; i < 8; i++) { 
		for (int j = 0; j < 8; j++) {
			m_values[i][j] -= 128;
		}
	}
}

void Block88::add128() {
	for (int i = 0; i < 8; i++) { 
		for (int j = 0; j < 8; j++) {
			m_values[i][j] += 128;
		}
	}
}

double alpha(double v) {
	if (v > 0)
		return 1;
	else
		return 1 / sqrt(2);
}

void Block88::forwardDCT() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			double sumi = 0;

			for (int x = 0; x < 8; x++) {
				for (int y = 0; y < 8; y++) {
					sumi += m_values[x][y] * cos(((2 * x + 1) * i * M_PI) / 16) * cos(((2 * y + 1) * j * M_PI) / 16);
				}
			}

			m_dValues[i][j] = 0.25 * alpha(i) * alpha(j) * sumi;
		}
	}
}

void Block88::inverseDCT() {
	for (int x = 0; x < 8; x++) {
		for (int y = 0; y < 8; y++) {
			double sumi = 0;

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					sumi += m_dValues[i][j] * cos(((2 * x + 1) * i * M_PI) / 16) * cos(((2 * y + 1) * j * M_PI) / 16) * alpha(i) * alpha(j);
				}
			}

			m_values[x][y] = 0.25 * sumi;
		}
	}
}

void Block88::quantize() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			double aux = m_dValues[i][j] / Q[i][j];
			m_values[i][j] = aux;
		}
	}
}

void Block88::deQuantize() {
	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			m_dValues[i][j] = m_values[i][j] * Q[i][j];
		}
	}
}

void Block88::toCoefArray() {
	int		t = 0;
	int		n = 8;
	int		m = 8;

	for (int i = 0; i < n + m - 1; i++)
	{
		if (i % 2 == 1) {
			// down left
			int x = i < n ? 0 : i - n + 1;
			int y = i < n ? i : n - 1;
			while (x < m && y >= 0)
				coefArray[t++] = m_values[x++][y--];
		} else {
			// up right
			int x = i < m ? i : m - 1;
			int y = i < m ? 0 : i - m + 1;
			while (x >= 0 && y < n)
				coefArray[t++] = m_values[x--][y++];
		}
	}
}

void Block88::fromCoefArray() {
	int		t = 0;
	int		n = 8;
	int		m = 8;

	for (int i = 0; i < n + m - 1; i++)
	{
		if (i % 2 == 1) {
			// down left
			int x = i < n ? 0 : i - n + 1;
			int y = i < n ? i : n - 1;
			while (x < m && y >= 0)
				m_values[x++][y--] = coefArray[t++];
		} else {
			// up right
			int x = i < m ? i : m - 1;
			int y = i < m ? 0 : i - m + 1;
			while (x >= 0 && y < n)
				m_values[x--][y++] = coefArray[t++];
		}
	}
}

int getSize(int amp) {
	if (amp >= -1 && amp <= 1)
		return 1;
	else if (amp >= -3 && amp <= 3)
		return 2;
	else if (amp >= -7 && amp <= 7)
		return 3;
	else if (amp >= -15 && amp <= 15)
		return 4;
	else if (amp >= -31 && amp <= 31)
		return 5;
	else if (amp >= -63 && amp <= 63)
		return 6;
	else if (amp >= -127 && amp <= 127)
		return 7;
	else if (amp >= -255 && amp <= 255)
		return 8;
	else if (amp >= -511 && amp <= 511)
		return 9;
	else if (amp >= -1023 && amp <= 1023)
		return 10;
	return 0;
}

void Block88::toDCCoefs() {
	firstDC = std::make_tuple(getSize(coefArray[0]), coefArray[0]);
	int	zeros = 0;

	for (int i = 1; i < 64; i++) {
		if (coefArray[i] == 0)
			zeros++;
		else {
			dcCoef.push_back(std::make_tuple(zeros, getSize(coefArray[i]), coefArray[i]));
			zeros = 0;
		}
	}
	if (zeros > 0)
		endsIn0 = true;
}

void Block88::fromDCCoefs() {
	coefArray[0] = std::get<1>(firstDC);
	int t = 1;

	for (auto &el: dcCoef) {
		int zeros = std::get<0>(el);
		int size = std::get<1>(el);
		int amp = std::get<2>(el);

		for (int i = 0; i < zeros; i++)
			coefArray[t++] = 0;

		coefArray[t++] = amp;
	}
	if (endsIn0) {
		for (int i = t; i < 64; i++)
			coefArray[i++] = 0;
	}

}
