#include <stdio.h>

typedef struct	s_vector
{
	int			len;
	int			v[200];
}				t_vector;

typedef struct	s_longest
{
	int			poz;
	int			len;
}				t_longest;

int				ft_power(int x, int n)
{
	/*
	 *	Computes the n-th power of x
	 *	Input: x, n
	 *	Output: power
	 */
	int		i;
	int		power;

	i = 0;
	power = 1;
	while (i < n)
	{
		power *= x;
		i++;
	}
	return (power);
}

t_vector		ft_read_vector(void)
{
	/*
	 *	Reads a vector of integers and returns it as a struct containg the vector and the lenght
	 *	Input: -
	 *	Output: vector
	 */
	t_vector	vector;
	int			i;

	printf("Give the length of the vector: ");
	vector.len = -1;
	while (vector.len < 0)
	{
		scanf("%d", &vector.len);
		if (vector.len < 0)
			printf("Please insert a positive value\n");
	}
	i = 0;
	while (i < vector.len)
	{
		printf("v[%d] = ", i);
		scanf("%d", &vector.v[i]);
		i++;
	}
	return (vector);
}

int				ft_sign(int a, int b)
{
	/*
	 *	Computes if two given numbers are of contrary signs
	 *	Input: a, b
	 *	Output: 1 if the numbers are of contrary signs, 0 otherwise
	 */
	if (a < 0 && b >= 0)
		return (1);
	if (a >= 0 && b < 0)
		return (1);
	return (0);
}

t_longest		ft_find_longest_subseq(t_vector vector)
{
	/*
	 *	Finds the longest subsequence with contiguous terms having contrary signs
	 *	Input: vector
	 *	Output: maxi - a struct containing the maximum subsequence starting position and length
	 */
	int			i;
	t_longest	l, maxi;

	i = 1;
	maxi.poz = 0;
	maxi.len = 1;
	l.poz = 0;
	l.len = 1;
	while (i < vector.len)
	{
		if (ft_sign(vector.v[i], vector.v[i - 1]))
			l.len++;
		else
		{
			if (l.len > maxi.len)
			{
				maxi.len = l.len;
				maxi.poz = l.poz;
			}
			l.poz = i;
			l.len = 1;
		}
		i++;
		if (l.len > maxi.len)
		{
			maxi.len = l.len;
			maxi.poz = l.poz;
		}
	}
	return (maxi);
}

void	ft_print_longest_subseq(t_vector vector, t_longest l)
{
	/*
	 *	Prints the longest subsequence with contiguous terms having contrary signs
	 *	Input: vector, l
	 *	Output: -
	 */
	int		i;

	i = l.poz;
	printf("The longest subsequence is:\n\t");
	while (i - l.poz < l.len - 1)
	{
		printf("%d, ", vector.v[i]);
		i++;
	}
	printf("%d\n", vector.v[i]);
}

char			ft_print_menu(void)
{
	/*
	 *	Prints the menu and reads the option and returns it
	 *	Input: -
	 *	Output: res
	 */
	char	res;

	printf("Please select the desired option:\n");
	printf("1. Determine the value x^n\n");
	printf("2. Find the longest contiguos subsequence such that any two consecutive elements have contrary signs\n");
	printf("0. Exit the program\n");
	scanf("%c", &res);
	return (res);
}

int				main(void)
{
	int			x, n;
	int			power;
	t_vector	vector;
	t_longest	l;
	int			res;

	while (1)
	{
		res = ft_print_menu();
		if (res == '0')
			break;
		else if (res == '1')
		{
			power = 0;
			printf("x = ");
			scanf("%d", &x);
			printf("n = ");
			n = -1;
			while (n < 0)
			{
				scanf("%d", &n);
				if (n < 0)
					printf("Please insert a positive value\n");
			}
			power = ft_power(x, n);
			printf("x^n = %d\n", power); 
		}
		else if (res == '2')
		{
			vector = ft_read_vector();
			l = ft_find_longest_subseq(vector);
			ft_print_longest_subseq(vector, l);
		}
		else
			printf("Unknown command!\n");
	}
	return (0);
}
