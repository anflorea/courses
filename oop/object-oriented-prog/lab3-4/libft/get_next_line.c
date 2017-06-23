/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   get_next_line.c                                    :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/13 19:57:59 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/13 22:47:22 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

static int	read_buffer(int const fd, char **text)
{
	char	*buff;
	int		result;
	char	*new;

	buff = (char*)malloc(sizeof(*buff) * (BUFF_SIZE + 1));
	if (!(buff))
		return (-1);
	result = read(fd, buff, BUFF_SIZE);
	if (result > 0)
	{
		buff[result] = '\0';
		new = ft_strjoin(*text, buff);
		free(*text);
		*text = new;
	}
	free(buff);
	return (result);
}

int			get_next_line(int const fd, char **line)
{
	static char		*text = NULL;
	int				result;
	char			*n_poz;

	if ((!(text) && (text = (char*)malloc(sizeof(*text))) == NULL) || !(line)
			|| BUFF_SIZE < 0 || fd < 0)
		return (-1);
	n_poz = ft_strchr(text, '\n');
	while (n_poz == NULL)
	{
		result = read_buffer(fd, &text);
		if (result == 0)
		{
			if (ft_strlen(text) == 0)
				return (0);
			text = ft_strjoin(text, "\n");
		}
		if (result < 0)
			return (-1);
		else
			n_poz = ft_strchr(text, '\n');
	}
	*line = ft_strsub(text, 0, ft_strlen(text) - ft_strlen(n_poz));
	text = ft_strdup(n_poz + 1);
	return (1);
}
