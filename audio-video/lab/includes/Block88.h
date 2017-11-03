#ifndef BLOCK_8_8_H
# define BLOCK_8_8_H

#include "ppmEncoder.h"

class RgbPixel;

class Block88 {
public:
	Block88(RgbPixel **matrix, int x, int y, char component);

	void subSample();
	void upSample();

	int getPositionX() { return m_positionX; }
	int getPositionY() { return m_positionY; }

	void printBlock();

//private:
	float m_values[8][8];
	float m_subValues[4][4];
	int m_positionX;
	int m_positionY;
};

#endif // BLOCK_8_8_H
