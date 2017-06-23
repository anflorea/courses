/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_lstmap.c                                        :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/10 19:42:11 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/10 20:18:31 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

t_list	*ft_lstmap(t_list *lst, t_list *(*f)(t_list *elem))
{
	t_list	*new;
	t_list	*new_list;
	t_list	*last;

	last = ft_lstnew(lst->content, lst->content_size);
	if (!last)
		return (NULL);
	last = (*f)(lst);
	new_list = last;
	while (lst->next)
	{
		lst = lst->next;
		new = ft_lstnew(lst->content, lst->content_size);
		if (!new)
			return (NULL);
		new = (*f)(lst);
		last->next = new;
		last = new;
	}
	return (new_list);
}
