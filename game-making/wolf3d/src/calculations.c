#include "wolf3d.h"

void	calculate_fps(t_env *env)
{
	struct timeval	t;

	gettimeofday(&t, NULL);
	env->player->old_time = env->player->time;
	env->player->time = t.tv_usec;
	env->player->fps = (int)(1.0 / ((env->player->time - env->player->old_time) / 1000000.0));
	if (env->player->time - env->player->static_time > 0.25 || env->player->static_time == 0)
	{
		env->player->static_time = env->player->time;
		env->player->static_fps = env->player->fps;
	}
}

void	ft_init_iteration(t_iteration *it, t_env *env)
{
	it->camera_x = 2 * it->x / (double)(WIDTH) - 1;
	it->ray_pos_x = env->player->position_x;
	it->ray_pos_y = env->player->position_y;
	it->ray_dir_x = env->player->direction_x + env->player->plane_x * it->camera_x;
	it->ray_dir_y = env->player->direction_y + env->player->plane_y * it->camera_x;
	it->map_x = (int)(it->ray_pos_x);
	it->map_y = (int)(it->ray_pos_y);
	it->delta_dist_x = sqrt(1 + (it->ray_dir_y * it->ray_dir_y) / (it->ray_dir_x * it->ray_dir_x));
	it->delta_dist_y = sqrt(1 + (it->ray_dir_x * it->ray_dir_x) / (it->ray_dir_y * it->ray_dir_y));
	it->hit = 0;
}

void	calculate_stuff(t_iteration *it)
{
	if (it->ray_dir_x < 0)
	{
		it->step_x = -1;
		it->side_dist_x = (it->ray_pos_x - it->map_x) * it->delta_dist_x;
	}
	else
	{
		it->step_x = 1;
		it->side_dist_x = (it->map_x + 1.0 - it->ray_pos_x) * it->delta_dist_x;
	}
	if (it->ray_dir_y < 0)
	{
		it->step_y = -1;
		it->side_dist_y = (it->ray_pos_y - it->map_y) * it->delta_dist_y;
	}
	else
	{
		it->step_y = 1;
		it->side_dist_y = (it->map_y + 1.0 - it->ray_pos_y) * it->delta_dist_y;
	}
}

void	perform_dda(t_iteration *it, t_env *env)
{
	while (it->hit == 0)
	{
		if (it->side_dist_x < it->side_dist_y)
		{
			it->side_dist_x += it->delta_dist_x;
			it->map_x += it->step_x;
			it->side = 2;
			if (it->ray_dir_x > 0)
				it->side = 0;
		}
		else
		{
			it->side_dist_y += it->delta_dist_y;
			it->map_y += it->step_y;
			it->side = 3;
			if (it->ray_dir_y > 0)
				it->side = 1;
		}
		if (env->map[it->map_x][it->map_y] > 0)
			it->hit = 1;
	}
}
