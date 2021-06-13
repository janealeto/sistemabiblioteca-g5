-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-06-2021 a las 00:36:29
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemabiblioteca-g5`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `idAutor` int(11) NOT NULL,
  `dni` int(11) DEFAULT NULL,
  `nombre` varchar(30) NOT NULL,
  `nacio` varchar(30) NOT NULL,
  `fechaNac` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplar`
--

CREATE TABLE `ejemplar` (
  `idEjemplar` int(11) NOT NULL,
  `estado` varchar(30) NOT NULL,
  `idLibro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `lector`
--

CREATE TABLE `lector` (
  `idLector` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `fechaNac` date NOT NULL,
  `estado` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `idLibro` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `editorial` varchar(50) NOT NULL,
  `anio` int(11) NOT NULL,
  `ejeEstado` varchar(50) NOT NULL,
  `idAutor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multa`
--

CREATE TABLE `multa` (
  `fechaCre` date NOT NULL,
  `fechaExp` date NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idMulta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `idPrestamo` int(11) NOT NULL,
  `fechaCre` date NOT NULL,
  `fechaExp` date NOT NULL,
  `estado` varchar(30) NOT NULL,
  `idLector` int(11) NOT NULL,
  `idEjemplar` int(11) NOT NULL,
  `idMulta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autor`
--
ALTER TABLE `autor`
  ADD PRIMARY KEY (`idAutor`),
  ADD UNIQUE KEY `dni_2` (`dni`),
  ADD KEY `dni` (`dni`);

--
-- Indices de la tabla `ejemplar`
--
ALTER TABLE `ejemplar`
  ADD PRIMARY KEY (`idEjemplar`),
  ADD KEY `idLibro` (`idLibro`);

--
-- Indices de la tabla `lector`
--
ALTER TABLE `lector`
  ADD PRIMARY KEY (`idLector`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `idDni` (`dni`);

--
-- Indices de la tabla `libro`
--
ALTER TABLE `libro`
  ADD PRIMARY KEY (`idLibro`),
  ADD KEY `idAutor` (`idAutor`);

--
-- Indices de la tabla `multa`
--
ALTER TABLE `multa`
  ADD PRIMARY KEY (`idMulta`);

--
-- Indices de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD PRIMARY KEY (`idPrestamo`),
  ADD KEY `idLector` (`idLector`),
  ADD KEY `idMulta` (`idMulta`),
  ADD KEY `idEjemplar` (`idEjemplar`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autor`
--
ALTER TABLE `autor`
  MODIFY `idAutor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ejemplar`
--
ALTER TABLE `ejemplar`
  MODIFY `idEjemplar` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `lector`
--
ALTER TABLE `lector`
  MODIFY `idLector` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `libro`
--
ALTER TABLE `libro`
  MODIFY `idLibro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `multa`
--
ALTER TABLE `multa`
  MODIFY `idMulta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `prestamo`
--
ALTER TABLE `prestamo`
  MODIFY `idPrestamo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ejemplar`
--
ALTER TABLE `ejemplar`
  ADD CONSTRAINT `ejemplar_ibfk_1` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idLibro`);

--
-- Filtros para la tabla `libro`
--
ALTER TABLE `libro`
  ADD CONSTRAINT `libro_ibfk_1` FOREIGN KEY (`idAutor`) REFERENCES `autor` (`idAutor`);

--
-- Filtros para la tabla `prestamo`
--
ALTER TABLE `prestamo`
  ADD CONSTRAINT `prestamo_ibfk_3` FOREIGN KEY (`idMulta`) REFERENCES `multa` (`idMulta`),
  ADD CONSTRAINT `prestamo_ibfk_4` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idEjemplar`),
  ADD CONSTRAINT `prestamo_ibfk_5` FOREIGN KEY (`idLector`) REFERENCES `lector` (`idLector`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
