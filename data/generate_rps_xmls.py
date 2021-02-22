import random

from xml_generator import generate_file

if __name__ == "__main__":
  for idx in range(1):
    cell_r = []
    cell_c = []
    cell_s = []
    for i in range(30):
      for j in range(30):
        cell_r.append(i)
        cell_c.append(j)
        cell_s.append(
            random.choice([0, 1, 2])
        )

    generate_file(
        f"RPSRandom{idx}.xml",
        "RPS", 30, 30,
        cell_r, cell_c, cell_s,
        speed=1, title=f"RPS random {idx + 1}", author='jt304',
        desc=f'RPS simulation {idx + 1}',
        sim_configs=dict(threshold=4),
    )
