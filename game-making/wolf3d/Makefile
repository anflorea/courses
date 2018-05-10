
NAME = wolf3d

SRCS =	src/main.c \
		src/utils.c \
		src/calculations.c \
		src/hook_functions.c \
		src/map_utils.c

OBJ =	main.o \
		utils.o \
		calculations.o \
		hook_functions.o \
		map_utils.o

all: $(NAME)

$(NAME):
	make -C libft/ fclean
	make -C libft/
	gcc -Wall -Werror -Wextra -I libft/ -I includes/ -I minilibx_macos -c $(SRCS)
	gcc -o $(NAME) $(OBJ) -L libft/ -lft -L minilibx_macos -lmlx -framework OpenGL -framework AppKit

clean:
	/bin/rm -f $(OBJ)
	make -C libft/ clean

fclean: clean
	/bin/rm -f $(NAME)
	/bin/rm -f libft/libft.a

re: fclean all
