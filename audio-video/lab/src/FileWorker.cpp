#include "FileWorker.h"

FileWorker::FileWorker(char *srcFile, char *destFile) {
	m_srcFile = srcFile;
	m_destFile = destFile;
}

void FileWorker::readPpm() {
	int n, m, r, g, b, maxi;
	FILE *fp;
	char str1[10], str2[200];

	fp = fopen(m_srcFile, "r");

	fgets(str1, 10, fp);
	fgets(str2, 200, fp);

	fscanf(fp, "%d%d%d", &n, &m, &maxi);

	m_m = m;
	m_n = n;

	printf("The picture %s is %dx%d pixels\n", m_srcFile, n, m);

	m_matrix = (RgbPixel**)malloc(sizeof(RgbPixel*) * n);
	for (int i = 0; i < n; i++) {
		m_matrix[i] = (RgbPixel*)malloc(sizeof(RgbPixel) * m);
		for (int j = 0; j < m; j++) {
			fscanf(fp, "%d%d%d", &r, &g, &b);

			RgbPixel pixel = RgbPixel(r, g, b);
			m_matrix[i][j] = pixel;
		}
	}
}

void FileWorker::writePpm() {
	FILE *fp;

	fp = fopen(m_destFile, "w");

	fprintf(fp, "P3\n# CREATOR: Flo\n");
	fprintf(fp, "%d %d\n255\n", m_n, m_m);

	for (int i = 0; i < m_n; i++) {
		for (int j = 0; j < m_m; j++) {
			fprintf(fp, "%d\n%d\n%d\n", m_matrix[i][j].getR(), m_matrix[i][j].getG(), m_matrix[i][j].getB());
		}
	}
}

void FileWorker::createPhoto(int x, int y) {
	m_n = x;
	m_m = y;

	m_matrix = (RgbPixel**)malloc(sizeof(RgbPixel*) * m_n);
	for (int i = 0; i < m_n; i++) {
		m_matrix[i] = (RgbPixel*)malloc(sizeof(RgbPixel) * m_m);
	}
}

void FileWorker::putPixel(int i, int j, RgbPixel pixel) {
	m_matrix[i][j] = pixel;
}
