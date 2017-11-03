#ifndef RGB_PIXEL_H
# define RGB_PIXEL_H

# include "ppmEncoder.h"

class RgbPixel
{
public:
	RgbPixel(int r, int g, int b);
	RgbPixel(float Y, float U, float V);

	int getR();
	int getG();
	int getB();

	float getY();
	float getU();
	float getV();

private:
	int m_red;
	int m_green;
	int m_blue;
};

#endif // RGB_PIXEL_H
