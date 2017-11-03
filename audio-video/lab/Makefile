
NAME = ppm-encoder

SRCS = src/main.cpp \
		src/utils.cpp \
		src/FileWorker.cpp \
		src/RgbPixel.cpp \
		src/Block88.cpp \

OBJ = main.o \
		utils.o \
		FileWorker.o \
		RgbPixel.o \
		Block88.o \

INCLUDES = includes/

all: $(NAME)

$(NAME):
	g++ -std=c++11 -I $(INCLUDES) -c $(SRCS)
	g++ -o $(NAME) $(OBJ)

clean:
	/bin/rm -f $(OBJ)
	
fclean: clean
	/bin/rm -f $(NAME)

re: fclean all
