#ifndef FILE_WORKER_H
# define FILE_WORKER_H

# include "ppmEncoder.h"
# include "RgbPixel.h"

class FileWorker {
public:
	FileWorker(char *srcFile, char *destFile);

	void readPpm();
	void writePpm();	

	int getHeight() { return m_n; }
	int getWidth() { return m_m; }

	RgbPixel** getPhoto() { return m_matrix; }

	void createPhoto(int x, int y);
	void putPixel(int i, int j, RgbPixel pixel);

private:
	char *m_srcFile;
	char *m_destFile;
	RgbPixel **m_matrix;
	int m_n;
	int m_m;
};

#endif // FILE_WORKER_H
