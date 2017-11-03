#ifndef RGB_PIXEL_H
# define RGB_PIXEL_H

# include "ppmEncoder.h"

class RgbPixel
{
public:
	RgbPixel(int r, int g, int b);
	RgbPixel();

	void fromYUV(int Y, int U, int V);

	int getR();
	int getG();
	int getB();

	int getY();
	int getU();
	int getV();

private:
	int m_red;
	int m_green;
	int m_blue;
};

#endif // RGB_PIXEL_H
