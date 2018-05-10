#include "wolf3d.h"

void	ft_expose_hook(t_env *env)
{
	t_iteration	*it;

	it = (t_iteration*)malloc(sizeof(t_iteration));
	it->x = 0;
	while ((it->x)++ < WIDTH - 1)
	{
		ft_init_iteration(it, env);
		calculate_stuff(it);
		perform_dda(it, env);
		if (it->side % 2 == 0)
			it->perp_wall_dist = (it->map_x - it->ray_pos_x + (1 - it->step_x) / 2) / it->ray_dir_x;
		else
			it->perp_wall_dist = (it->map_y - it->ray_pos_y + (1 - it->step_y) / 2) / it->ray_dir_y;
		it->line_height = (int)(HEIGHT / it->perp_wall_dist);
		it->draw_start = -it->line_height / 2 + HEIGHT / 2;
		if (it->draw_start < 0)
			it->draw_start = 0;
		it->draw_end = it->line_height / 2 + HEIGHT / 2;
		if (it->draw_end > HEIGHT)
			it->draw_end = HEIGHT - 1;
		draw_line(it, env);
	}
}

int		loop_hook(t_env *env)
{
	int		bits;
	int		size_line;
	int		endian;

	calculate_fps(env);
	move_player(env);
	bits = 32;
	size_line = WIDTH;
	endian = 0;
	env->img = mlx_new_image(env->mlx, WIDTH, HEIGHT);
	env->string = mlx_get_data_addr(env->img, &bits, &size_line, &endian);
	ft_expose_hook(env);
	mlx_clear_window(env->mlx, env->window);
	mlx_put_image_to_window(env->mlx, env->window, env->img, 0, 0);
	mlx_destroy_image(env->mlx, env->img);
	mlx_string_put(env->mlx, env->window, 30, 30, 0xFF0000, ft_itoa(env->player->static_fps));
	return (0);
}

int		key_pressed_hook(int keycode, t_env *env)
{
	if (keycode == 53)
		exit(0);
	if (keycode == 257)
		env->player->flash_mode = 1;
	if (keycode == 8)
		env->map_type = CLASIC;
	if (keycode == 45)
		env->map_type = NOISE;
	if (keycode == 126)
		env->player->move_front = 1;
	if (keycode == 125)
		env->player->move_back = 1;
	if (keycode == 124)
		env->player->rotate_right = 1;
	if (keycode == 123)
		env->player->rotate_left = 1;
	return (0);
}

int		key_released_hook(int keycode, t_env *env)
{
	if (keycode == 257)
		env->player->flash_mode = 0;
	if (keycode == 126)
		env->player->move_front = 0;
	if (keycode == 125)
		env->player->move_back = 0;
	if (keycode == 124)
		env->player->rotate_right = 0;
	if (keycode == 123)
		env->player->rotate_left = 0;
	return (0);
}
