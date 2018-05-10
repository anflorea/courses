/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_bubble_sort.c                                   :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/12 18:01:11 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/12 18:39:32 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

void	ft_bubble_sort(int *v, size_t size, int (*cmp)(int, int))
{
	size_t	i;
	int		aux;
	int		ok;

	ok = 1;
	while (ok)
	{
		ok = 0;
		i = 1;
		while (i < size)
		{
			if (cmp(v[i - 1], v[i]) > 0)
			{
				aux = v[i];
				v[i] = v[i - 1];
				v[i - 1] = aux;
				ok = 1;
			}
			i++;
		}
	}
}
