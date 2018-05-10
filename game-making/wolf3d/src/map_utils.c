#include "wolf3d.h"

void	ft_get_map_dimensions(char *filename, t_env *env)
{
	int		fd;
	int		counter;
	char	*line;

	fd = open(filename, O_RDONLY);
	if (fd < 0)
	{
		perror(filename);
		exit(0);
	}
	env->map_name = ft_strdup(filename);
	get_next_line(fd, &line);
	env->map_width = ft_count_char(line, ' ') + 1;
	free(line);
	counter = 1;
	while (get_next_line(fd, &line))
	{
		if (ft_count_char(line, ' ') + 1 != env->map_width)
		{
			ft_putstr("Map error!\n");
			exit(0);
		}
		counter++;
		free(line);
	}
	env->map_height = counter;
	close(fd);
}

void	ft_resolve_line(t_env *env, char *line, int i)
{
	int		j;
	int		z;
	int		nbr;

	z = 0;
	j = 0;
	nbr = 0;
	while (line[z] != '\0' && line[z] != '\n')
	{
		if (line[z] == ' ')
		{
			env->map[i][j++] = nbr;
			nbr = 0;
		}
		else if (ft_isdigit(line[z]))
			nbr = nbr * 10 + line[z] - '0';
		else
		{
			ft_putstr("Map error: Invalid character in map!\n");
			exit(0);
		}
		z++;
	}
	if (ft_isdigit(line[z - 1]))
		env->map[i][j] = nbr;
}

void	ft_read_map(char *filename, t_env *env)
{
	int		fd;
	char	*line;
	int		i;

	fd = open(filename, O_RDONLY);
	env->map = (char**)malloc(sizeof(char*) * env->map_height + 10);
	i = 0;
	while (i < env->map_height)
	{
		env->map[i] = (char*)malloc(sizeof(char) * env->map_width + 10);
		get_next_line(fd, &line);
		ft_resolve_line(env, line, i);
		free(line);
		i++;
	}
}

void	validate_edges(t_env *env)
{
	int		i;
	int		j;

	i = 0;
	while (i < env->map_height)
	{
		j = 0;
		while (j < env->map_width)
		{
			if ((i == 0 || j == 0 || i == env->map_height - 1 ||
				j == env->map_width - 1) && env->map[i][j] == 0)
			{
				ft_putstr("Map error: Map edge can not be 0\n");
				exit(0);
			}
			j++;
		}
		i++;
	}
}

void	ft_resolve_map(int argc, char **argv, t_env *env)
{
	if (argc != 2)
	{
		ft_putstr("Usage: ./wolf3d <map>\n");
		exit(0);
	}
	else
	{
		ft_get_map_dimensions(argv[1], env);
		ft_read_map(argv[1], env);
		validate_edges(env);
		if (env->map_width < 4 || env->map_height < 4)
		{
			ft_putstr("Map error: Map too small (map has to be at least 4/4)\n");
			exit(0);
		}
		// TODO: Remove this before deployment
		//printf("map height: %d, map width: %d\n", env->map_height, env->map_width);
	}
}
