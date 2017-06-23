/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_qsort.c                                         :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/12 20:50:00 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/12 21:14:56 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

static void	qqsort(int *a, int left, int right, int (*cmp)(int, int))
{
	int		i;
	int		j;
	int		x;
	int		y;

	i = left;
	j = right;
	x = a[(left + right) / 2];
	while (i <= j)
	{
		while (cmp(a[i], x) < 0)
			i++;
		while (cmp(x, a[j]) < 0)
			j--;
		if (i <= j)
		{
			y = a[i];
			a[i++] = a[j];
			a[j--] = y;
		}
	}
	if (left < j)
		qqsort(a, left, j, cmp);
	if (i < right)
		qqsort(a, i, right, cmp);
}

void		ft_qsort(int *v, size_t size, int (*cmp)(int, int))
{
	qqsort(v, 0, (int)(size) - 1, cmp);
	(void)(cmp);
}
