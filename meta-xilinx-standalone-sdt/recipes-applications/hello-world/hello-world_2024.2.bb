inherit esw deploy python3native esw_apps_common

ESW_COMPONENT_SRC = "/lib/sw_apps/hello_world/src/"

DEPENDS += "libxil xiltimer"

ESW_EXECUTABLE_NAME = "hello_world"

do_configure:prepend() {
    (
    cd ${S}
    lopper ${DTS_FILE} -- baremetallinker_xlnx.py ${ESW_MACHINE} ${S}/${ESW_COMPONENT_SRC}
    install -m 0755 *.cmake ${S}/${ESW_COMPONENT_SRC}/
    cp -rf ${S}/scripts/linker_files/ ${S}/${ESW_COMPONENT_SRC}/linker_files
    install -m 0644 ${S}/cmake/UserConfig.cmake ${S}/${ESW_COMPONENT_SRC}
    )
}

ESW_CUSTOM_LINKER_FILE ?= "None"
EXTRA_OECMAKE = "-DCUSTOM_LINKER_FILE=${@d.getVar('ESW_CUSTOM_LINKER_FILE')}"

FILES:${PN} = "${base_libdir}/firmware/hello_world*"
