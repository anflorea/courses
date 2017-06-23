/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strstr.c                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/04 14:16:05 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/10 17:15:49 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

char	*ft_strstr(const char *s1, const char *s2)
{
	int		i;
	int		j;
	int		aux;

	i = 0;
	if (!(*s2))
		return (char*)(s1);
	while (s1[i] != '\0')
	{
		j = 0;
		aux = i;
		while (s1[aux] != '\0' && s2[j] != '\0' && s1[aux] == s2[j])
		{
			aux++;
			j++;
		}
		if (s2[j] == '\0')
			return (char*)(s1 + i);
		i++;
	}
	return (NULL);
}
