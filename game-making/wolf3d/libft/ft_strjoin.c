/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strjoin.c                                       :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/10 15:41:43 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/10 15:45:35 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

char	*ft_strjoin(char const *s1, char const *s2)
{
	int		len1;
	int		len2;
	char	*new;
	int		i;

	len1 = ft_strlen(s1);
	len2 = ft_strlen(s2);
	new = (char*)malloc(sizeof(char) * (len1 + len2 + 1));
	if (new)
	{
		i = 0;
		while (s1[i] != '\0')
		{
			new[i] = s1[i];
			i++;
		}
		while (s2[i - len1] != '\0')
		{
			new[i] = s2[i - len1];
			i++;
		}
		new[i] = '\0';
	}
	return (new);
}
