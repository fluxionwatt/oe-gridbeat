SUMMARY = "GridBeat gateway image"
LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL:append = " \
  networkmanager \
  chrony \
  firewalld nftables \
  swupdate swupdate-www \
  gridbeat \
"

