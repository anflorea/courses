#include "bakery.h"

t_listt	*create_list_elem(t_material *material)
{
	t_listt	*elem;

	elem = (t_listt*)malloc(sizeof(t_listt));
	elem->next = NULL;
	elem->material = material;
	return (elem);
}

void	append_undo(t_listt **undoList, t_listt **list)
{
	t_listt *it;

	it = *list;
	while (it)
	{
		list_push_back(undoList, it->material);
		it = it->next;
	}
}

void	list_push_back(t_listt **begin_list, t_material *material)
{
	t_listt	*list;

	list = *begin_list;
	if (!list)
		*begin_list = create_list_elem(material);
	else
	{
		while (list->next)
			list = list->next;
		list->next = create_list_elem(material);
	}
}

t_material	*getElementByName(t_listt **begin_list, char *name)
{
	t_listt *list;

	list = *begin_list;
	while (list)
	{
		if (list && ft_strcmp(get_name(list->material), name) == 0)
			return (list->material);
		list = list->next;
	}
	return (NULL);
}

void	free_offer_memory(t_material *material)
{
	free(material->name);
	free(material->supplier);
	free(material->date);
}

void	ft_lstdelonee(t_listt **alst, void (*del)(t_material *))
{
	//(*del)((*alst)->material);
	//free(*alst);
	*alst = NULL;
}

void	ft_lstdell_all(t_listt **list, void (*del)(t_material *))
{
	t_listt	*it, *prev;

	it = *list;
	if (it)
	{
		while (it->next)
		{
			prev = it;
			it = it->next;
			ft_lstdelonee(&prev, del);
		}
		ft_lstdelonee(&it, del);
	}
}

void	ft_lstdell(t_listt **alst, void (*del)(t_material *), char *name)
{
	t_listt	*temp, *prev;
	temp = *alst;
	while (temp)
		if (ft_strcmp(temp->material->name, name) == 0)
		{
			if (temp == *alst)
			{
				*alst = temp->next;
				ft_lstdelonee(&temp, del);
			}
			else
			{
				prev->next = temp->next;
				ft_lstdelonee(&temp, del);
			}
		}
		else
		{
			prev = temp;
			temp = temp->next;
		}
}
