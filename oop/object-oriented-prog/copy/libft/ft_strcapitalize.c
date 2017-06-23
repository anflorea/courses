/* ************************************************************************** */
/*                                                                            */
/*                                                        :::      ::::::::   */
/*   ft_strcapitalize.c                                 :+:      :+:    :+:   */
/*                                                    +:+ +:+         +:+     */
/*   By: anflorea <marvin@42.fr>                    +#+  +:+       +#+        */
/*                                                +#+#+#+#+#+   +#+           */
/*   Created: 2015/11/13 16:51:48 by anflorea          #+#    #+#             */
/*   Updated: 2015/11/13 16:56:54 by anflorea         ###   ########.fr       */
/*                                                                            */
/* ************************************************************************** */

#include "libft.h"

char	*ft_strcapitalize(char *str)
{
	int		i;

	i = 0;
	while (str[i] != '\0')
	{
		if (i == 0)
		{
			if (ft_isalpha(str[i]))
				str[i] = ft_toupper(str[i]);
		}
		else
		{
			if (ft_isalpha(str[i]) && (str[i - 1] == ' ' || str[i - 1] == '\t'))
				str[i] = ft_toupper(str[i]);
			else if (ft_isalpha(str[i]))
				str[i] = ft_tolower(str[i]);
		}
		i++;
	}
	return (str);
}
