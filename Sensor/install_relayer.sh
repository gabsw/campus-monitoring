#!/usr/bin/env bash

# Installs requests for the data relaying
pip install requests

# Installs everything the sensor needs to work
curl https://get.pimoroni.com/bme680 | bash