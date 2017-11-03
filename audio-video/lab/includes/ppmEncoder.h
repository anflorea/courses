#ifndef PPM_ENCODER_H
# define PPM_ENCODER_H

# include <stdio.h>
# include <unistd.h>
# include <string.h>
# include <stdlib.h>
# include <vector>

void errorExit(void);
void printUsage(void);
bool checkFile(char *fileName);
int clamp(int value);

#endif // PPM_ENCODER_H
