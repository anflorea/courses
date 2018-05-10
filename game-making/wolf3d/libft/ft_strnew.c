/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strnew.c                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/10 15:00:26 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/10 15:09:43 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

char	*ft_strnew(size_t size)
{
	size_t	i;
	char	*str;

	str = (char*)malloc(sizeof(char) * (size + 1));
	if (str)
	{
		i = 0;
		while (i <= size)
		{
			str[i] = '\0';
			i++;
		}
	}
	return (str);
}
