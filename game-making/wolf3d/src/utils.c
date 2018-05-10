#include "wolf3d.h"

int		ft_count_char(char *str, char c)
{
	int		i;
	int		counter;

	i = 0;
	counter = 0;
	while (str[i] != '\0')
	{
		if (str[i] == c)
			counter++;
		i++;
	}
	return (counter);
}

int		ft_get_color(t_iteration *it, t_env *env)
{
	int		color;

	if (env->map_type == CLASIC)
		return it->color;
	else
	{
		color = arc4random() % 255;
		if (it->side == 0)
			color = color << 8;
		if (it->side == 1)
			color = color << 16;
		if (it->side == 2)
			color = color + (color << 8);
		return (color);
	}
}
