#include "RgbPixel.h"

RgbPixel::RgbPixel(int r, int g, int b) {
	m_red = r;
	m_green = g;
	m_blue = b;
}

RgbPixel::RgbPixel(float Y, float U, float V) {
	//m_red = 1.164 * (Y - 16) + 1.596 * (V - 128);
	//m_green = 1.164 * (Y - 16) - 0.813 * (V - 128) - 0.391 * (U - 128);
	//m_blue = 1.164 * (Y - 16) + 2.018 * (U - 128);
	m_red = Y + 1.140 * V;
	m_green = Y - 0.395 * U - 0.581 * V;
	m_blue = Y + 2.033 * U;
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

float RgbPixel::getY() {
	return 0.299 * m_red + 0.587 * m_green + 0.114 * m_blue;
	//return 0.257 * m_red + 0.504 * m_green + 0.098 * m_blue + 16;
}

float RgbPixel::getU() {
	//return 128 - 0.1687 * m_red - 0.3312 * m_green + 0.5 * m_blue;
	//return 128 - 0.148 * m_red - 0.291 * m_green + 0.439 * m_blue;
	return 0.492 * (m_blue - getY());
}

float RgbPixel::getV() {
	//return 128 + 0.5 * m_red - 0.4186 * m_green - 0.0813 * m_blue;
	//return 128 + 0.439 * m_red - 0.368 * m_green - 0.071 * m_blue;
	return 0.877 * (m_red - getY());
}
