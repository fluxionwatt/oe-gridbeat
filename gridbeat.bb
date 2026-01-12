SUMMARY = "GridBeat from prebuilt RPM (per-MACHINE URL)"
LICENSE = "CLOSED"

inherit bin_package systemd

# -----------------------------
# 1) per-MACHINE RPM URL
# -----------------------------
SRC_URI:qemuarm64   = "https://github.com/fluxionwatt/gridbeat/releases/download/v1.0.0/gridbeat-1.0.0-aarch64.rpm;downloadfilename=gridbeat-${PV}-${MACHINE}.rpm;name=gridbeat;subdir=${BPN}"
SRC_URI:qemux86-64  = "https://github.com/fluxionwatt/gridbeat/releases/download/v1.0.0/gridbeat-1.0.0-x86_64.rpm;downloadfilename=gridbeat-${PV}-${MACHINE}.rpm;name=gridbeat;subdir=${BPN}"
SRC_URI:stm32mp257  = "https://github.com/fluxionwatt/gridbeat/releases/download/v1.0.0/gridbeat-1.0.0-aarch64.rpm;downloadfilename=gridbeat-${PV}-${MACHINE}.rpm;name=gridbeat;subdir=${BPN}"

# 如果你的 STM32MP257 MACHINE 不是 "stm32mp257"，而是类似 "stm32mp257dk"，
# 就用真实 MACHINE 名做 override：
# SRC_URI:stm32mp257dk = "https://.../gridbeat-...aarch64.rpm;name=gridbeat;subdir=${BPN}"

# -----------------------------
# 2) per-MACHINE checksum (强烈建议写)
#    这里用 name=gridbeat 的 varflag 写法
# -----------------------------
SRC_URI[gridbeat.sha256sum]:qemuarm64  = "6d92048bb81db1ab63e7340f66aeb7a1a4947e9a82c3584b08bde33ccaab8e6e"
SRC_URI[gridbeat.sha256sum]:qemux86-64 = "4710b4068ae638886882a7323faca38977c7b78c817f2fff21ccf40b3216bd37"
SRC_URI[gridbeat.sha256sum]:stm32mp257 = "6d92048bb81db1ab63e7340f66aeb7a1a4947e9a82c3584b08bde33ccaab8e6e"

# -----------------------------
# 3) RPM 解包后的目录（重要：不要用 S=${WORKDIR}）
# -----------------------------
S = "${UNPACKDIR}/${BPN}"

# -----------------------------
# 4) systemd service
#    - 如果 RPM 里已经自带 gridbeat.service（通常在 /usr/lib/systemd/system/）
#      可以直接启用
# -----------------------------
SYSTEMD_SERVICE:${PN} = "gridbeat.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

# 某些第三方 RPM 二进制可能已 strip，会触发 QA
# 需要时打开：
# INSANE_SKIP:${PN} += "already-stripped"

# 可选：限制只允许这几个 MACHINE 使用（避免误用）
COMPATIBLE_MACHINE = "qemuarm64|qemux86-64|stm32mp257|stm32mp257.*"


