#include "RgbPixel.h"
#include "ppmEncoder.h"

RgbPixel::RgbPixel(int r, int g, int b) {
	m_red = r;
	m_green = g;
	m_blue = b;
}

RgbPixel::RgbPixel() {
	m_red = 0;
	m_green = 0;
	m_blue = 0;
}

void RgbPixel::fromYUV(int Y, int U, int V) {
	m_red = clamp(Y + 1.140 * V);
	m_green = clamp(Y - 0.395 * U - 0.581 * V);
	m_blue = clamp(Y + 2.033 * U);
}

int RgbPixel::getR() {
	return m_red;
}

int RgbPixel::getG() {
	return m_green;
}

int RgbPixel::getB() {
	return m_blue;
}


int RgbPixel::getY() {
	return 0.299 * m_red + 0.587 * m_green + 0.114 * m_blue;
}

int RgbPixel::getU() {
	return 0.492 * (m_blue - getY());
}

int RgbPixel::getV() {
	return 0.877 * (m_red - getY());
}
