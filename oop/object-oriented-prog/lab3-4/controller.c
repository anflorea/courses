#include "bakery.h"

int		add_material(t_listt **list, t_material *material)
{
	t_material	*oldMaterial;

	oldMaterial = getElementByName(list, get_name(material));
	if (oldMaterial == NULL)
	{
		list_push_back(list, material);
		return (1);
	}
	set_quantity(oldMaterial, get_quantity(oldMaterial) + get_quantity(material));
	return (0);
}

int		compute_undo(t_listt **undoList, t_listt **list, t_listt **redoList)
{
	*redoList = *list;
	*list = *undoList;
	return (1);
}

void	compute_redo(t_listt **undoList, t_listt **list, t_listt **redoList)
{
	*undoList = *list;
	*list = *redoList;
}

void	append_dem_undo(t_listt **undoList, t_listt **list)
{
	//ft_lstdell_all(list, free_offer_memory);
	append_undo(undoList, list);
}

void	swap(t_listt *a, t_listt *b)
{
	t_material	*aux;

	aux = a->material;
	a->material = b->material;
	b->material = aux;
}

void	bubbleSort(t_listt **start)
{
	int swapped;
	t_listt *ptr1;
	t_listt *lptr = NULL;

	/* Checking for empty list */
	if (*start == NULL)
		return;
	do
	{
		swapped = 0;
		ptr1 = *start;

		while (ptr1->next != lptr)
		{
			if (ft_strcmp(get_name(ptr1->material), get_name(ptr1->next->material)) > 0)
			{ 
				swap(ptr1, ptr1->next);
				swapped = 1;
			}
			ptr1 = ptr1->next;
		}
		lptr = ptr1;
	}
	while (swapped);
}

int		date_cmp(t_date *date1, t_date *date2)
{
	if (date1->year != date2->year)
		return (date1->year - date2->year);
	if (date1->month != date2->month)
		return (date1->month - date2->month);
	return (date1->day - date2->day);
}

t_listt	**getSortedListByName(t_listt **newList, t_listt **list, char *name, t_date *date)
{
	t_listt *it;

	it = *list;
	while (it)
	{
		if (ft_strstr(get_name(it->material), name) && date_cmp(it->material->date, date) < 0)
			list_push_back(newList, it->material);
		it = it->next;
	}
	bubbleSort(newList);
	return (newList);
}

t_listt	**getSortedListBySupplier(t_listt **newList, t_listt **list, char *supplier, int quantity)
{
	t_listt *it;

	it = *list;
	while (it)
	{
		if (ft_strcmp(get_supplier(it->material), supplier) == 0 && get_quantity(it->material) <= quantity)
			list_push_back(newList, it->material);
		it = it->next;
	}
	bubbleSort(newList);
	return (newList);
}

int		update_material(t_listt **list, t_material *material)
{
	t_material	*oldMaterial;

	oldMaterial = getElementByName(list, get_name(material));
	if (oldMaterial)
	{
		set_supplier(oldMaterial, get_supplier(material));
		set_quantity(oldMaterial, get_quantity(material));
		set_day(oldMaterial, get_day(material));
		set_month(oldMaterial, get_month(material));
		set_year(oldMaterial, get_year(material));
		return (1);
	}
	return (0);
}

int		remove_material(t_listt **list, char *name)
{
	if (getElementByName(list, name))
	{
		ft_lstdell(list, free_offer_memory, name);
		return (1);
	}
	return (0);
}
