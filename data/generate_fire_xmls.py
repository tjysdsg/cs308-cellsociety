import random

from xml_generator import generate_file

if __name__ == "__main__":
  generate_file(
      "FireEdge0.xml",
      "Fire", 18, 20,
      [0 for i in range(20)],
      [i for i in range(20)],
      [1, 1, 2, 1, 1,
       2, 1, 1, 2, 1,
       1, 2, 1, 1, 2,
       1, 1, 2, 1, 1],
      speed=20, title="Fire edge 1", author='jt304',
      desc='Fire edge 1',
      sim_configs=dict(probCatch=0.6),
  )

  generate_file(
      "FireEdge1.xml",
      "Fire", 10, 15,
      [i for i in range(10)],
      [0 for i in range(10)],
      [1, 1, 2, 1, 1,
       2, 1, 1, 2, 1],
      speed=20, title="Fire edge 2", author='jt304',
      desc='Fire simulation 2',
      sim_configs=dict(probCatch=0.6),
  )

  for idx in range(4):
    cell_r = []
    cell_c = []
    cell_s = []
    for i in range(15):
      for j in range(15):
        cell_r.append(i)
        cell_c.append(j)
        cell_s.append(random.randint(1, 2))

    generate_file(
        f"FireRandom{idx}.xml",
        "Fire", 15, 15,
        cell_r, cell_c, cell_s,
        speed=20, title=f"Fire random {idx + 1}", author='jt304',
        desc=f'Fire simulation {idx + 1}',
        sim_configs=dict(probCatch=0.6),
    )
