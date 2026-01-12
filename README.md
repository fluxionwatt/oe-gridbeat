# oe-gridbeat


### Build

##### Host 配置
```bash
dnf install -y epel-release
dnf install -y chrpath lz4 rpcgen
```

```bash
# source code
git clone https://github.com/fluxionwatt/oe-gridbeat

# start build
cd oe-gridbeat

cd layers

# Yocto / poky
git clone -b walnascar git://git.yoctoproject.org/poky

# meta-openembedded（包含 meta-oe / meta-networking / meta-python 等子层）
git clone -b walnascar https://github.com/openembedded/meta-openembedded.git

# meta-swupdate（walnascar 分支存在）
git clone -b walnascar https://github.com/sbabic/meta-swupdate.git

# STM32MP257 用（walnascar 分支在 layer index 中存在）
git clone -b walnascar https://github.com/STMicroelectronics/meta-st-stm32mp.git

```

```
cd /root/oe-gridbeat
source layers/poky/oe-init-build-env build

bitbake mc:aarch64:gridbeat-image -c fetch
bitbake mc:aarch64:gridbeat-image --runall=fetch

bitbake mc:aarch64:uninative-tarball -c fetch || true

bitbake mc:aarch64:gridbeat-image
bitbake mc:x86_64:gridbeat-image mc:aarch64:gridbeat-image mc:stm32mp257:gridbeat-image
```

