/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   strncpy.c                                          :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/10/28 16:10:41 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/03 16:18:49 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

char	*ft_strncpy(char *dst, const char *src, size_t n)
{
	size_t	i;
	int		ok;

	i = 0;
	ok = 1;
	while (i < n)
	{
		if (ok)
			dst[i] = src[i];
		else
			dst[i] = '\0';
		if (src[i] == '\0')
			ok = 0;
		i++;
	}
	return (dst);
}
