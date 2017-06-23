/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_lstdel.c                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/10 19:29:48 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/10 19:57:00 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

void	ft_lstdel(t_list **alst, void (*f)(void *, size_t))
{
	if ((*alst)->next)
		ft_lstdel(&((*alst)->next), f);
	(*f)(&((*alst)->content), (*alst)->content_size);
	free(*alst);
	*alst = NULL;
}
