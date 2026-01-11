SUMMARY = "GridBeat (prebuilt)"
LICENSE = "CLOSED"

SRC_URI = "\
  file://gridbeat \
  file://gridbeat.service \
"

inherit systemd

SYSTEMD_SERVICE:${PN} = "gridbeat.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/gridbeat ${D}${bindir}/gridbeat

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/gridbeat.service ${D}${systemd_system_unitdir}/gridbeat.service
}

FILES:${PN} += "${bindir}/gridbeat ${systemd_system_unitdir}/gridbeat.service"

