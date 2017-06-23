NAME = bakery_app

SRC = main.c ui.c domain.c controller.c repository.c

OBJ = $(SRC:.c=.o)

all: $(NAME)

$(NAME):
	make -C libft/ fclean
	make -C libft/ 
	gcc -Wall -c $(SRC) -I libft/
	gcc -o $(NAME) $(OBJ) -L libft/ -lft
	rm $(OBJ)

clean:

fclean: clean
	rm $(NAME)

re: fclean all
