/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strsplit_all.c                                  :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: nmuresan <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/12/16 12:04:08 by nmuresan          #+#    #+#             */
/*   Updated: 2015/12/16 13:17:55 by nmuresan         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

static int		number_of_words(char const *s, char c)
{
	int		nb;
	int		i;

	i = 0;
	nb = 0;
	while (s[i])
	{
		if (s[i] == c)
			nb++;
		i++;
	}
	return (nb + 1);
}

char			**ft_strsplit_all(char const *s, char c)
{
	char	**new;
	size_t	i;
	int		n;
	size_t	j;

	new = NULL;
	new = (char**)malloc(sizeof(*new) * (number_of_words(s, c) + 1));
	if (new)
	{
		i = 0;
		n = 0;
		while (i < ft_strlen(s))
		{
			j = i;
			while (s[j] != c && s[j])
				j++;
			new[n] = ft_strsub(s, i, j - i);
			++n;
			i = j + 1;
		}
		new[n] = 0;
	}
	return (new);
}
