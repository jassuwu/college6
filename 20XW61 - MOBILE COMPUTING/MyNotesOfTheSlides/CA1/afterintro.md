`Page 30 - 49`

# Frequncies For Communication

![Image](../assets/FrequenciesForCommunication.png?raw=true "Frequencies For Communication")

## Wireless Frequency Allocation

Radio frequencies range from 9KHz to 400GHZ according to ITU.

- **The microwave frequency** range is from 1 GHz to 40 GHz and is suitable for point-to-point transmission and used for satellite communications.
- **The radio frequency** range is from 30 MHz to 1 GHz and is suitable for omnidirectional applications.
- **The infrared frequency** range is roughly from 3x10^11 to 2x10^14 Hz and is useful in local point-to-point multipoint applications within confined areas.

## Frequencies used for mobile communication.

- VHF/UHF ranges are used for mobile radio and have reliable connections with small antennas.
- SHF and higher frequencies are used for directed radio links and satellite communication with large bandwidth available.
- Wireless LANs use frequencies in the UHF to SHF spectrum with some systems planned up to EHF.
  - However, there are limitations due to absorption by water and oxygen molecules and weather-dependent fading.

## Frequency regulations for mobile communication.

- Frequencies from 9KHz to 300 MHz are in high demand, especially VHF (30-300MHz).
- There are two unlicensed bands:
  - Industrial, Science and Medicine (ISM) at 2.4 GHz and Unlicensed National Information Infrastructure (UNII) at 5.2 GHz.
- Different agencies license and regulate frequencies such as the FCC in the US, ETSI in Europe, WPC in India and ITU for international coordination.
- There are regional, national and international issues to consider as well as procedures for military, emergency and air traffic control.

## Wireless Transmission

- Wireless communication systems consist of transmitters, antennas that radiate electromagnetic energy into the air, and receivers.
- In some cases, transmitters and receivers are on the same device called transceivers

## Transmitters

![Image](../assets/Transmitters.png?raw=true "Transmitters")

To generate a signal at 900 MHz from an original source at 300 MHz, an **amplifier** strengthens the initial signal. An **oscillator** creates a carrier wave of 600 MHz. A **mixer** combines the signal with the **oscillator** and produces 900 MHz while also performing modulation. A **filter** selects the correct frequency and another **amplifier** strengthens the signal before sending it.

## Antennas

An antenna is an electrical conductor or system of conductors used for radiating electromagnetic energy into space or for collecting electromagnetic energy from space. It is an integral part of a wireless system.

### Radiation Patterns

An antenna radiates power in all directions but typically does not radiate equally in all directions. An ideal antenna is one that radiates equal power in all directions and is called an **isotropic** antenna. All points with equal power are located on a sphere with the antenna as its center.

### Antenna Types

- Omni-directional

  ![Image](../assets/OmnidirectionalAntenna.png?raw=true "Omni-directional antenna")

  - Produces omnidirectional radiation pattern of equal strength in all directions
  - Vector A and B are of equal length

- Directional

  ![Image](../assets/DirectionalAntenna.png?raw=true "Directional antenna")

  - Produces a directional radiation pattern with a main lobe in a particular direction
  - Vector B is longer than vector A

- Dipole

  ![Image](../assets/DipoleAntenna.png?raw=true "Dipole antenna")

  - Half-wave dipole antenna consists of two straight collinear conductors of equal length
  - Length is 1/2 wavelength of the signal.

- Quarter-wave

  ![Image](../assets/QuarterWaveAntenna.png?raw=true "Quarter-wave antenna")

  - Quarter-wave or marconi antenna has a veritcal conductor.
  - Length is 1/4 wavelength of the signal.

- Sectorized

  ![Image](../assets/SectorizedAntenna.png?raw=true "Sectorized antenna")

  - Several directional antenna combined on a single pole to provide sectorized antenna.
  - each sector serves receivers listening it its direction.

## Comparison of Omni-directional and Directional Antennas

| Issues        | Omni-directional | Directional |
| ------------- | ---------------- | ----------- |
| Spatial reuse | Low              | High        |
| Connectivity  | Low              | High        |
| Interference  | High             | Low         |
| Cost          | Low              | High        |

# Signals

- They are physical representation of data that varies with time and location.
- Signals can be classified based on whether they are continuous or discrete in time and value.
  - Analog signals have continuous time and values while digital signals have discrete time and values.
- Periodic signals have specific parameters such as period, frequency, amplitude, and phase shift.
  - A sine wave is a special type of periodic signal used as a carrier.

## Signal Propagation Ranges

Three ranges for signal transmission: transmission range, detection range, and interference range.

- In the ransmission range, communication is possible with a low error rate.
- In the detection range, the signal can be detected but communication is not possible.
- In the interference range, the signal may not be detected and adds to background noise.

  ![Image](../assets/SignalPropagationRanges.png?raw=true "Signal Propagation Ranges")

## Signal Propagation

- Propagation in free space is always like light (in a straight line)
- The receiving power is proportional to 1/d² (where d is the distance between sender and receiver).
- The receiving power can also be influenced by factors such as fading, shadowing, reflection at large obstacles, refraction depending on the density of a medium, scattering at small obstacles and diffraction at edges.

  ![Image](../assets/SignalPropagation.png?raw=true "Signal Propagation")

## Multipath Propagation

- Signal can take many different paths between sender and receiver due to reflection, scattering and diffraction.

  ![Image](../assets/MultipathPropagation.png?raw=true "Multipath Propagation")

- This can cause time dispersion where the signal is dispersed over time and interference with neighboring symbols (Inter Symbol Interference or ISI).
- The signal can also reach a receiver directly and phase shifted which can result in a distorted signal depending on the phases of the different parts.

## Effects of Mobility

Channel characteristics can change over time and location.

- This can cause changes in signal paths, delay variations of different signal parts and phases of signal parts which can result in quick changes in the power received (short term fading).

  ![Image](../assets/EffectsOfMobility.png?raw=true "Effects of Mobility")

- Additional changes such as distance to sender and obstacles further away can cause slow changes in the average power received (long term fading).

## Propagation Models

- Ground wave propagation

  ![Image](../assets/GroundWavePropagation.png?raw=true "Ground wave propagation")

- Sky wave propagation

  ![Image](../assets/SkyWavePropagation.png?raw=true "Sky wave propagation")

- Line of sight propagation

  ![Image](../assets/LineOfSightPropagation.png?raw=true "Line of sight propagation")
