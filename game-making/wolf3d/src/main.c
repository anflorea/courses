#include "wolf3d.h"

void		draw_line(t_iteration *it, t_env *env)
{
	int		y;

	it->color = 0xF9FFC1;
	if (it->side == 0)
		it->color = 0xEB3349;
	if (it->side == 1)
		it->color = 0x9EFFC1;
	if (it->side == 2)
		it->color = 0x9EFFF5;
	y = it->draw_start;
	while (y++ < HEIGHT - 1)
	{
		if (y < it->draw_end)
			*(int *)(env->string + (y * WIDTH + it->x) * 4) = ft_get_color(it, env);
		else
			*(int *)(env->string + (y * WIDTH + it->x) * 4) = 0xEEEEEE;
	}
}

void		move_player(t_env *env)
{
	double	value;

	value = 0.1;
	if (env->player->flash_mode)
		value = 0.4;
	if (env->player->rotate_right)
		ft_rotate_player(env, -0.05);
	if (env->player->rotate_left)
		ft_rotate_player(env, 0.05);
	if (env->player->move_front)
	{
		if (!env->map[(int)(env->player->position_x + env->player->direction_x * value * 3)][(int)env->player->position_y])
			env->player->position_x += env->player->direction_x * value;
		if (!env->map[(int)env->player->position_x][(int)(env->player->position_y + env->player->direction_y * value * 3)])
			env->player->position_y += env->player->direction_y * value;
	}
	if (env->player->move_back)
	{
		if (!env->map[(int)(env->player->position_x - env->player->direction_x * value * 3)][(int)env->player->position_y])
			env->player->position_x -= env->player->direction_x * value;
		if (!env->map[(int)env->player->position_x][(int)(env->player->position_y - env->player->direction_y * value * 3)])
			env->player->position_y -= env->player->direction_y * value;
	}
}

void		ft_rotate_player(t_env *env, double rot_speed)
{
	double old_dir_x;
	double old_plane_x;

	old_dir_x = env->player->direction_x;
	env->player->direction_x = old_dir_x * cos(rot_speed) - env->player->direction_y * sin(rot_speed);
	env->player->direction_y = old_dir_x * sin(rot_speed) + env->player->direction_y * cos(rot_speed);
	old_plane_x = env->player->plane_x;
	env->player->plane_x = old_plane_x * cos(rot_speed) - env->player->plane_y * sin(rot_speed);
	env->player->plane_y = old_plane_x * sin(rot_speed) + env->player->plane_y * cos(rot_speed);
}

t_player	*ft_create_player(t_env *env)
{
	t_player	*player;

	player = (t_player*)malloc(sizeof(t_player));
	player->position_y = (int)(env->map_width / 2);
	player->position_x = (int)(env->map_height / 2);
	env->map[env->map_height / 2][env->map_width / 2] = 0;
	player->direction_x = -1;
	player->direction_y = 0;
	player->plane_x = 0;
	player->plane_y = 0.7;
	player->time = 0;
	player->old_time = 0;
	player->static_time = 0;
	player->static_fps = 0;
	player->move_front = 0;
	player->move_back = 0;
	player->rotate_left = 0;
	player->rotate_right = 0;
	player->flash_mode = 0;
	return (player);
}

int			main(int argc, char **argv)
{
	t_env	*env;

	env = (t_env*)malloc(sizeof(t_env));
	ft_resolve_map(argc, argv, env);
	env->mlx = mlx_init();
	env->window = mlx_new_window(env->mlx, WIDTH, HEIGHT, "Wolf 3D");
	env->player = ft_create_player(env);
	env->map_type = CLASIC;
	mlx_loop_hook(env->mlx, loop_hook, env);
	mlx_hook(env->window, 2, (1L << 0), key_pressed_hook, env);
	mlx_hook(env->window, 3, (1L << 1), key_released_hook, env);
	mlx_loop(env->mlx);
	return (0);
}
