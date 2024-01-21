# libsmartmair
Unofficial Java client for Mitsubishi air conditioners. This client allows for communication with Mitsubishi Smart M-Air Wi-Fi Modules.
The client is intended for use over the local network only.

## Disclaimer
This is an unofficial implementation which might lead to unexpected results. Use at your own risk. 
This client communicates solely over the local network and does not communicate with proprietary servers of any kind.

## Done
- Get device info
- Get air conditioner state (LED, power status, operation mode, inside/outside temperature, airflow setting, wind direction settings, vacant property, leave home mode, model no)
- Set air conditioner state (LED, power status, operation mode, airflow setting, wind direction settings, self cleaning setting, vacant property, leave home mode)
- Unit tests for the AirconStateCoder
- Usage example

## Todo
- Add unit tests for the HTTP client
- Add some info on network discovery (NsdManager is used on Android)
- Try to dig up more info on result codes

## Good to know
- Account IDs and device IDs can just be random values though UUIDs are used officially
- Operator ID is an account ID
- It seems that an account has to be created once with updateAccountInfo before setAirconStat works