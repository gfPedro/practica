package com.practica.oee.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class Paginacion {

	private static final int TAMANIO_PAGINA_DEFECTO = 100;

	public static PageRequest obtenerPaginable(Integer pagina, Integer tamanioPagina, String orden) {
		if (StringUtils.isNotBlank(orden)) {
			return PageRequest.of(obtenerCriterioPagina(pagina), obtenerCriterioTamanioPagina(tamanioPagina),
					obtenerCriterioOrden(orden));
		}
		return PageRequest.of(obtenerCriterioPagina(pagina), obtenerCriterioTamanioPagina(tamanioPagina));
	}

	private static Integer obtenerCriterioPagina(Integer pagina) {
		return (pagina == null || (pagina - 1) <= 0) ? 0 : (pagina - 1);
	}

	private static Integer obtenerCriterioTamanioPagina(Integer tamanioPagina) {
		return (tamanioPagina == null || tamanioPagina <= 0) ? TAMANIO_PAGINA_DEFECTO : tamanioPagina;
	}

	private static Sort obtenerCriterioOrden(String orden) {
		return Sort
				.by(("-".equals(String.valueOf(orden.charAt(0)))) ? Order.desc(orden.substring(1)) : Order.asc(orden));
	}
}
