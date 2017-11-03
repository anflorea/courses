#include "ppmEncoder.h"

int clamp(int value) {
	if (value > 255)
		return 255;
	if (value < 0)
		return 0;
	return value;
}

void errorExit() {
	exit(0);
}

void printUsage() {
	printf("usage: ./ppmEncoder [encode |  decode] file\n");
	errorExit();
}

bool checkFile(char *fileName) {
	// TODO: Check that the file exists and it has read rights
	return true;
}

