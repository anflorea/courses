#include "Block88.h"

#include "RgbPixel.h"

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
