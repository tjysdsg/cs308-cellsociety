from xml_generator import generate_file

if __name__ == "__main__":
    generate_file(
        "Fire", 10, 15,
        [9, 9], [14, 14], [1, 2],
        speed=20, title="Fire simulation", author='jt304', desc='Fire simulation 1',
        sim_configs=dict(probCatch=0.6),
    )
