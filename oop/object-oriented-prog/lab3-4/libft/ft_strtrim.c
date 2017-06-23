/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strtrim.c                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/10 15:46:24 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/10 17:20:15 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

static int	ft_getlen(char const *s)
{
	int		i;
	int		len;

	i = 0;
	while (s[i] == ' ' || s[i] == '\n' || s[i] == '\t')
		i++;
	len = 0;
	while (s[i] != '\0')
	{
		i++;
		len++;
	}
	if (i)
		i--;
	while ((s[i] == ' ' || s[i] == '\n' || s[i] == '\t') && len)
	{
		i--;
		len--;
	}
	return (len);
}

char		*ft_strtrim(char const *s)
{
	int		i;
	int		len;
	char	*new;
	int		start;

	len = ft_getlen(s);
	new = (char*)malloc(sizeof(char) * (len + 1));
	if (new)
	{
		i = 0;
		while (s[i] == ' ' || s[i] == '\n' || s[i] == '\t')
			i++;
		start = i;
		while (i - start < len)
		{
			new[i - start] = s[i];
			i++;
		}
		new[i] = '\0';
	}
	return (new);
}
