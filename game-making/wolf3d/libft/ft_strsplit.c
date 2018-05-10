/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strsplit.c                                      :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/10 16:48:48 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/10 18:43:43 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

static int	ft_count_words(char const *str, char c)
{
	int		i;
	int		words;

	i = 0;
	words = 0;
	while (str[i] != '\0')
	{
		if (i == 0)
		{
			if (str[i] != c)
				words++;
		}
		else
		{
			if (str[i] != c && str[i - 1] == c)
				words++;
		}
		i++;
	}
	return (words);
}

static int	ft_getlen(char const *s, int i, char c)
{
	int		len;

	len = 0;
	while (s[i] != c && s[i] != '\0')
	{
		i++;
		len++;
	}
	return (len);
}

char		**ft_strsplit(char const *s, char c)
{
	char	**new;
	int		words;
	int		i;

	words = ft_count_words(s, c);
	new = (char**)malloc(sizeof(char*) * (words + 1));
	if (new)
	{
		i = 0;
		words = 0;
		while (s[i] != '\0')
		{
			while (s[i] == c)
				i++;
			if (s[i] != '\0')
			{
				new[words] = ft_strsub(s, i, (size_t)ft_getlen(s, i, c));
				words++;
			}
			while (s[i] != c && s[i] != '\0')
				i++;
		}
		new[words] = 0;
	}
	return (new);
}
