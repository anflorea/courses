#include "ppmEncoder.h"

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

