#include "bakery.h"

t_date	*date_constructor(int day, int month, int year)
{
	t_date	*date;

	date = (t_date*)malloc(sizeof(t_date));
	date->day = day;
	date->month = month;
	date->year = year;
	return (date);
}

t_material	*material_constructor(char *name, char *supplier, int quantity, t_date *date)
{
	t_material *material;

	material = (t_material*)malloc(sizeof(t_material));
	material->name = name;
	material->supplier = supplier;
	material->quantity = quantity;
	material->date = date;
	return (material);
}

char	*print_material(t_material *material)
{
	char	*str;

	str = ft_strdup("Name: ");
	str = ft_strjoin(str, material->name);
	str = ft_strjoin(str, ", Supplier: ");
	str = ft_strjoin(str, material->supplier);
	str = ft_strjoin(str, ", quantity: ");
	str = ft_strjoin(str, ft_itoa(material->quantity));
	str = ft_strjoin(str, ", Expiration date: ");
	str = ft_strjoin(str, ft_itoa(material->date->day));
	str = ft_strjoin(str, "/");
	str = ft_strjoin(str, ft_itoa(material->date->month));
	str = ft_strjoin(str, "/");
	str = ft_strjoin(str, ft_itoa(material->date->year));
	str = ft_strjoin(str, "\n");
	return (str);
}

char	*get_name(t_material *material)
{
	return (material->name);
}

char	*get_supplier(t_material *material)
{
	return (material->supplier);
}

int		get_quantity(t_material *material)
{
	return (material->quantity);
}

int		get_day(t_material *material)
{
	return (material->date->day);
}

int		get_month(t_material *material)
{
	return (material->date->month);
}

int		get_year(t_material *material)
{
	return (material->date->year);
}

void	set_name(t_material *material, char *name)
{
	material->name = name;
}

void	set_supplier(t_material *material, char *supplier)
{
	material->supplier = supplier;
}

void	set_quantity(t_material *material, int quantity)
{
	material->quantity = quantity;
}

void	set_day(t_material *material, int day)
{
	material->date->day = day;
}

void	set_month(t_material *material, int month)
{
	material->date->month = month;
}

void	set_year(t_material *material, int year)
{
	material->date->year = year;
}
