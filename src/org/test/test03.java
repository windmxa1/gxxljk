package org.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.model.HostBean;
import org.tools.JsonUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class test03 {
	public static void main(String[] args) {
		try {
			while (true) {
				String result = "{\"command_code\":\"5000002\",\"description\":\"realtime wave\",\"seq_num\":\"0\",\"data_id\":\"20170414111746\",\"length\":\"5000\",\"data\":[0,0.005,0.01,0.015,0.02,0.025,0.03,0.035,0.04,0.045,0.05,0.055,0.06,0.065,0.07,0.075,0.08,0.085,0.09,0.095,0.1,0.105,0.11,0.115,0.12,0.125,0.13,0.135,0.14,0.145,0.15,0.155,0.16,0.165,0.17,0.175,0.18,0.185,0.19,0.195,0.2,0.205,0.21,0.215,0.22,0.225,0.23,0.235,0.24,0.245,0.25,0.255,0.26,0.265,0.27,0.275,0.28,0.285,0.29,0.295,0.3,0.305,0.31,0.315,0.32,0.325,0.33,0.335,0.34,0.345,0.35,0.355,0.36,0.365,0.37,0.375,0.38,0.385,0.39,0.395,0.4,0.405,0.41,0.415,0.42,0.425,0.43,0.435,0.44,0.445,0.45,0.455,0.46,0.465,0.47,0.475,0.48,0.485,0.49,0.495,0.5,0.505,0.51,0.515,0.52,0.525,0.53,0.535,0.54,0.545,0.55,0.555,0.56,0.565,0.57,0.575,0.58,0.585,0.59,0.595,0.6,0.605,0.61,0.615,0.62,0.625,0.63,0.635,0.64,0.645,0.65,0.655,0.66,0.665,0.67,0.675,0.68,0.685,0.69,0.695,0.7,0.705,0.71,0.715,0.72,0.725,0.73,0.735,0.74,0.745,0.75,0.755,0.76,0.765,0.77,0.775,0.78,0.785,0.79,0.795,0.8,0.805,0.81,0.815,0.82,0.825,0.83,0.835,0.84,0.845,0.85,0.855,0.86,0.865,0.87,0.875,0.88,0.885,0.89,0.895,0.9,0.905,0.91,0.915,0.92,0.925,0.93,0.935,0.94,0.945,0.95,0.955,0.96,0.965,0.97,0.975,0.98,0.985,0.99,0.995,1,1.005,1.01,1.015,1.02,1.025,1.03,1.035,1.04,1.045,1.05,1.055,1.06,1.065,1.07,1.075,1.08,1.085,1.09,1.095,1.1,1.105,1.11,1.115,1.12,1.125,1.13,1.135,1.14,1.145,1.15,1.155,1.16,1.165,1.17,1.175,1.18,1.185,1.19,1.195,1.2,1.205,1.21,1.215,1.22,1.225,1.23,1.235,1.24,1.245,1.25,1.255,1.26,1.265,1.27,1.275,1.28,1.285,1.29,1.295,1.3,1.305,1.31,1.315,1.32,1.325,1.33,1.335,1.34,1.345,1.35,1.355,1.36,1.365,1.37,1.375,1.38,1.385,1.39,1.395,1.4,1.405,1.41,1.415,1.42,1.425,1.43,1.435,1.44,1.445,1.45,1.455,1.46,1.465,1.47,1.475,1.48,1.485,1.49,1.495,1.5,1.505,1.51,1.515,1.52,1.525,1.53,1.535,1.54,1.545,1.55,1.555,1.56,1.565,1.57,1.575,1.58,1.585,1.59,1.595,1.6,1.605,1.61,1.615,1.62,1.625,1.63,1.635,1.64,1.645,1.65,1.655,1.66,1.665,1.67,1.675,1.68,1.685,1.69,1.695,1.7,1.705,1.71,1.715,1.72,1.725,1.73,1.735,1.74,1.745,1.75,1.755,1.76,1.765,1.77,1.775,1.78,1.785,1.79,1.795,1.8,1.805,1.81,1.815,1.82,1.825,1.83,1.835,1.84,1.845,1.85,1.855,1.86,1.865,1.87,1.875,1.88,1.885,1.89,1.895,1.9,1.905,1.91,1.915,1.92,1.925,1.93,1.935,1.94,1.945,1.95,1.955,1.96,1.965,1.97,1.975,1.98,1.985,1.99,1.995,2,2.005,2.01,2.015,2.02,2.025,2.03,2.035,2.04,2.045,2.05,2.055,2.06,2.065,2.07,2.075,2.08,2.085,2.09,2.095,2.1,2.105,2.11,2.115,2.12,2.125,2.13,2.135,2.14,2.145,2.15,2.155,2.16,2.165,2.17,2.175,2.18,2.185,2.19,2.195,2.2,2.205,2.21,2.215,2.22,2.225,2.23,2.235,2.24,2.245,2.25,2.255,2.26,2.265,2.27,2.275,2.28,2.285,2.29,2.295,2.3,2.305,2.31,2.315,2.32,2.325,2.33,2.335,2.34,2.345,2.35,2.355,2.36,2.365,2.37,2.375,2.38,2.385,2.39,2.395,2.4,2.405,2.41,2.415,2.42,2.425,2.43,2.435,2.44,2.445,2.45,2.455,2.46,2.465,2.47,2.475,2.48,2.485,2.49,2.495,2.5,2.505,2.51,2.515,2.52,2.525,2.53,2.535,2.54,2.545,2.55,2.555,2.56,2.565,2.57,2.575,2.58,2.585,2.59,2.595,2.6,2.605,2.61,2.615,2.62,2.625,2.63,2.635,2.64,2.645,2.65,2.655,2.66,2.665,2.67,2.675,2.68,2.685,2.69,2.695,2.7,2.705,2.71,2.715,2.72,2.725,2.73,2.735,2.74,2.745,2.75,2.755,2.76,2.765,2.77,2.775,2.78,2.785,2.79,2.795,2.8,2.805,2.81,2.815,2.82,2.825,2.83,2.835,2.84,2.845,2.85,2.855,2.86,2.865,2.87,2.875,2.88,2.885,2.89,2.895,2.9,2.905,2.91,2.915,2.92,2.925,2.93,2.935,2.94,2.945,2.95,2.955,2.96,2.965,2.97,2.975,2.98,2.985,2.99,2.995,3,3.005,3.01,3.015,3.02,3.025,3.03,3.035,3.04,3.045,3.05,3.055,3.06,3.065,3.07,3.075,3.08,3.085,3.09,3.095,3.1,3.105,3.11,3.115,3.12,3.125,3.13,3.135,3.14,3.145,3.15,3.155,3.16,3.165,3.17,3.175,3.18,3.185,3.19,3.195,3.2,3.205,3.21,3.215,3.22,3.225,3.23,3.235,3.24,3.245,3.25,3.255,3.26,3.265,3.27,3.275,3.28,3.285,3.29,3.295,3.3,3.305,3.31,3.315,3.32,3.325,3.33,3.335,3.34,3.345,3.35,3.355,3.36,3.365,3.37,3.375,3.38,3.385,3.39,3.395,3.4,3.405,3.41,3.415,3.42,3.425,3.43,3.435,3.44,3.445,3.45,3.455,3.46,3.465,3.47,3.475,3.48,3.485,3.49,3.495,3.5,3.505,3.51,3.515,3.52,3.525,3.53,3.535,3.54,3.545,3.55,3.555,3.56,3.565,3.57,3.575,3.58,3.585,3.59,3.595,3.6,3.605,3.61,3.615,3.62,3.625,3.63,3.635,3.64,3.645,3.65,3.655,3.66,3.665,3.67,3.675,3.68,3.685,3.69,3.695,3.7,3.705,3.71,3.715,3.72,3.725,3.73,3.735,3.74,3.745,3.75,3.755,3.76,3.765,3.77,3.775,3.78,3.785,3.79,3.795,3.8,3.805,3.81,3.815,3.82,3.825,3.83,3.835,3.84,3.845,3.85,3.855,3.86,3.865,3.87,3.875,3.88,3.885,3.89,3.895,3.9,3.905,3.91,3.915,3.92,3.925,3.93,3.935,3.94,3.945,3.95,3.955,3.96,3.965,3.97,3.975,3.98,3.985,3.99,3.995,4,4.005,4.01,4.015,4.02,4.025,4.03,4.035,4.04,4.045,4.05,4.055,4.06,4.065,4.07,4.075,4.08,4.085,4.09,4.095,4.1,4.105,4.11,4.115,4.12,4.125,4.13,4.135,4.14,4.145,4.15,4.155,4.16,4.165,4.17,4.175,4.18,4.185,4.19,4.195,4.2,4.205,4.21,4.215,4.22,4.225,4.23,4.235,4.24,4.245,4.25,4.255,4.26,4.265,4.27,4.275,4.28,4.285,4.29,4.295,4.3,4.305,4.31,4.315,4.32,4.325,4.33,4.335,4.34,4.345,4.35,4.355,4.36,4.365,4.37,4.375,4.38,4.385,4.39,4.395,4.4,4.405,4.41,4.415,4.42,4.425,4.43,4.435,4.44,4.445,4.45,4.455,4.46,4.465,4.47,4.475,4.48,4.485,4.49,4.495,4.5,4.505,4.51,4.515,4.52,4.525,4.53,4.535,4.54,4.545,4.55,4.555,4.56,4.565,4.57,4.575,4.58,4.585,4.59,4.595,4.6,4.605,4.61,4.615,4.62,4.625,4.63,4.635,4.64,4.645,4.65,4.655,4.66,4.665,4.67,4.675,4.68,4.685,4.69,4.695,4.7,4.705,4.71,4.715,4.72,4.725,4.73,4.735,4.74,4.745,4.75,4.755,4.76,4.765,4.77,4.775,4.78,4.785,4.79,4.795,4.8,4.805,4.81,4.815,4.82,4.825,4.83,4.835,4.84,4.845,4.85,4.855,4.86,4.865,4.87,4.875,4.88,4.885,4.89,4.895,4.9,4.905,4.91,4.915,4.92,4.925,4.93,4.935,4.94,4.945,4.95,4.955,4.96,4.965,4.97,4.975,4.98,4.985,4.99,4.995,0,0.005,0.01,0.015,0.02,0.025,0.03,0.035,0.04,0.045,0.05,0.055,0.06,0.065,0.07,0.075,0.08,0.085,0.09,0.095,0.1,0.105,0.11,0.115,0.12,0.125,0.13,0.135,0.14,0.145,0.15,0.155,0.16,0.165,0.17,0.175,0.18,0.185,0.19,0.195,0.2,0.205,0.21,0.215,0.22,0.225,0.23,0.235,0.24,0.245,0.25,0.255,0.26,0.265,0.27,0.275,0.28,0.285,0.29,0.295,0.3,0.305,0.31,0.315,0.32,0.325,0.33,0.335,0.34,0.345,0.35,0.355,0.36,0.365,0.37,0.375,0.38,0.385,0.39,0.395,0.4,0.405,0.41,0.415,0.42,0.425,0.43,0.435,0.44,0.445,0.45,0.455,0.46,0.465,0.47,0.475,0.48,0.485,0.49,0.495,0.5,0.505,0.51,0.515,0.52,0.525,0.53,0.535,0.54,0.545,0.55,0.555,0.56,0.565,0.57,0.575,0.58,0.585,0.59,0.595,0.6,0.605,0.61,0.615,0.62,0.625,0.63,0.635,0.64,0.645,0.65,0.655,0.66,0.665,0.67,0.675,0.68,0.685,0.69,0.695,0.7,0.705,0.71,0.715,0.72,0.725,0.73,0.735,0.74,0.745,0.75,0.755,0.76,0.765,0.77,0.775,0.78,0.785,0.79,0.795,0.8,0.805,0.81,0.815,0.82,0.825,0.83,0.835,0.84,0.845,0.85,0.855,0.86,0.865,0.87,0.875,0.88,0.885,0.89,0.895,0.9,0.905,0.91,0.915,0.92,0.925,0.93,0.935,0.94,0.945,0.95,0.955,0.96,0.965,0.97,0.975,0.98,0.985,0.99,0.995,1,1.005,1.01,1.015,1.02,1.025,1.03,1.035,1.04,1.045,1.05,1.055,1.06,1.065,1.07,1.075,1.08,1.085,1.09,1.095,1.1,1.105,1.11,1.115,1.12,1.125,1.13,1.135,1.14,1.145,1.15,1.155,1.16,1.165,1.17,1.175,1.18,1.185,1.19,1.195,1.2,1.205,1.21,1.215,1.22,1.225,1.23,1.235,1.24,1.245,1.25,1.255,1.26,1.265,1.27,1.275,1.28,1.285,1.29,1.295,1.3,1.305,1.31,1.315,1.32,1.325,1.33,1.335,1.34,1.345,1.35,1.355,1.36,1.365,1.37,1.375,1.38,1.385,1.39,1.395,1.4,1.405,1.41,1.415,1.42,1.425,1.43,1.435,1.44,1.445,1.45,1.455,1.46,1.465,1.47,1.475,1.48,1.485,1.49,1.495,1.5,1.505,1.51,1.515,1.52,1.525,1.53,1.535,1.54,1.545,1.55,1.555,1.56,1.565,1.57,1.575,1.58,1.585,1.59,1.595,1.6,1.605,1.61,1.615,1.62,1.625,1.63,1.635,1.64,1.645,1.65,1.655,1.66,1.665,1.67,1.675,1.68,1.685,1.69,1.695,1.7,1.705,1.71,1.715,1.72,1.725,1.73,1.735,1.74,1.745,1.75,1.755,1.76,1.765,1.77,1.775,1.78,1.785,1.79,1.795,1.8,1.805,1.81,1.815,1.82,1.825,1.83,1.835,1.84,1.845,1.85,1.855,1.86,1.865,1.87,1.875,1.88,1.885,1.89,1.895,1.9,1.905,1.91,1.915,1.92,1.925,1.93,1.935,1.94,1.945,1.95,1.955,1.96,1.965,1.97,1.975,1.98,1.985,1.99,1.995,2,2.005,2.01,2.015,2.02,2.025,2.03,2.035,2.04,2.045,2.05,2.055,2.06,2.065,2.07,2.075,2.08,2.085,2.09,2.095,2.1,2.105,2.11,2.115,2.12,2.125,2.13,2.135,2.14,2.145,2.15,2.155,2.16,2.165,2.17,2.175,2.18,2.185,2.19,2.195,2.2,2.205,2.21,2.215,2.22,2.225,2.23,2.235,2.24,2.245,2.25,2.255,2.26,2.265,2.27,2.275,2.28,2.285,2.29,2.295,2.3,2.305,2.31,2.315,2.32,2.325,2.33,2.335,2.34,2.345,2.35,2.355,2.36,2.365,2.37,2.375,2.38,2.385,2.39,2.395,2.4,2.405,2.41,2.415,2.42,2.425,2.43,2.435,2.44,2.445,2.45,2.455,2.46,2.465,2.47,2.475,2.48,2.485,2.49,2.495,2.5,2.505,2.51,2.515,2.52,2.525,2.53,2.535,2.54,2.545,2.55,2.555,2.56,2.565,2.57,2.575,2.58,2.585,2.59,2.595,2.6,2.605,2.61,2.615,2.62,2.625,2.63,2.635,2.64,2.645,2.65,2.655,2.66,2.665,2.67,2.675,2.68,2.685,2.69,2.695,2.7,2.705,2.71,2.715,2.72,2.725,2.73,2.735,2.74,2.745,2.75,2.755,2.76,2.765,2.77,2.775,2.78,2.785,2.79,2.795,2.8,2.805,2.81,2.815,2.82,2.825,2.83,2.835,2.84,2.845,2.85,2.855,2.86,2.865,2.87,2.875,2.88,2.885,2.89,2.895,2.9,2.905,2.91,2.915,2.92,2.925,2.93,2.935,2.94,2.945,2.95,2.955,2.96,2.965,2.97,2.975,2.98,2.985,2.99,2.995,3,3.005,3.01,3.015,3.02,3.025,3.03,3.035,3.04,3.045,3.05,3.055,3.06,3.065,3.07,3.075,3.08,3.085,3.09,3.095,3.1,3.105,3.11,3.115,3.12,3.125,3.13,3.135,3.14,3.145,3.15,3.155,3.16,3.165,3.17,3.175,3.18,3.185,3.19,3.195,3.2,3.205,3.21,3.215,3.22,3.225,3.23,3.235,3.24,3.245,3.25,3.255,3.26,3.265,3.27,3.275,3.28,3.285,3.29,3.295,3.3,3.305,3.31,3.315,3.32,3.325,3.33,3.335,3.34,3.345,3.35,3.355,3.36,3.365,3.37,3.375,3.38,3.385,3.39,3.395,3.4,3.405,3.41,3.415,3.42,3.425,3.43,3.435,3.44,3.445,3.45,3.455,3.46,3.465,3.47,3.475,3.48,3.485,3.49,3.495,3.5,3.505,3.51,3.515,3.52,3.525,3.53,3.535,3.54,3.545,3.55,3.555,3.56,3.565,3.57,3.575,3.58,3.585,3.59,3.595,3.6,3.605,3.61,3.615,3.62,3.625,3.63,3.635,3.64,3.645,3.65,3.655,3.66,3.665,3.67,3.675,3.68,3.685,3.69,3.695,3.7,3.705,3.71,3.715,3.72,3.725,3.73,3.735,3.74,3.745,3.75,3.755,3.76,3.765,3.77,3.775,3.78,3.785,3.79,3.795,3.8,3.805,3.81,3.815,3.82,3.825,3.83,3.835,3.84,3.845,3.85,3.855,3.86,3.865,3.87,3.875,3.88,3.885,3.89,3.895,3.9,3.905,3.91,3.915,3.92,3.925,3.93,3.935,3.94,3.945,3.95,3.955,3.96,3.965,3.97,3.975,3.98,3.985,3.99,3.995,4,4.005,4.01,4.015,4.02,4.025,4.03,4.035,4.04,4.045,4.05,4.055,4.06,4.065,4.07,4.075,4.08,4.085,4.09,4.095,4.1,4.105,4.11,4.115,4.12,4.125,4.13,4.135,4.14,4.145,4.15,4.155,4.16,4.165,4.17,4.175,4.18,4.185,4.19,4.195,4.2,4.205,4.21,4.215,4.22,4.225,4.23,4.235,4.24,4.245,4.25,4.255,4.26,4.265,4.27,4.275,4.28,4.285,4.29,4.295,4.3,4.305,4.31,4.315,4.32,4.325,4.33,4.335,4.34,4.345,4.35,4.355,4.36,4.365,4.37,4.375,4.38,4.385,4.39,4.395,4.4,4.405,4.41,4.415,4.42,4.425,4.43,4.435,4.44,4.445,4.45,4.455,4.46,4.465,4.47,4.475,4.48,4.485,4.49,4.495,4.5,4.505,4.51,4.515,4.52,4.525,4.53,4.535,4.54,4.545,4.55,4.555,4.56,4.565,4.57,4.575,4.58,4.585,4.59,4.595,4.6,4.605,4.61,4.615,4.62,4.625,4.63,4.635,4.64,4.645,4.65,4.655,4.66,4.665,4.67,4.675,4.68,4.685,4.69,4.695,4.7,4.705,4.71,4.715,4.72,4.725,4.73,4.735,4.74,4.745,4.75,4.755,4.76,4.765,4.77,4.775,4.78,4.785,4.79,4.795,4.8,4.805,4.81,4.815,4.82,4.825,4.83,4.835,4.84,4.845,4.85,4.855,4.86,4.865,4.87,4.875,4.88,4.885,4.89,4.895,4.9,4.905,4.91,4.915,4.92,4.925,4.93,4.935,4.94,4.945,4.95,4.955,4.96,4.965,4.97,4.975,4.98,4.985,4.99,4.995,0,0.005,0.01,0.015,0.02,0.025,0.03,0.035,0.04,0.045,0.05,0.055,0.06,0.065,0.07,0.075,0.08,0.085,0.09,0.095,0.1,0.105,0.11,0.115,0.12,0.125,0.13,0.135,0.14,0.145,0.15,0.155,0.16,0.165,0.17,0.175,0.18,0.185,0.19,0.195,0.2,0.205,0.21,0.215,0.22,0.225,0.23,0.235,0.24,0.245,0.25,0.255,0.26,0.265,0.27,0.275,0.28,0.285,0.29,0.295,0.3,0.305,0.31,0.315,0.32,0.325,0.33,0.335,0.34,0.345,0.35,0.355,0.36,0.365,0.37,0.375,0.38,0.385,0.39,0.395,0.4,0.405,0.41,0.415,0.42,0.425,0.43,0.435,0.44,0.445,0.45,0.455,0.46,0.465,0.47,0.475,0.48,0.485,0.49,0.495,0.5,0.505,0.51,0.515,0.52,0.525,0.53,0.535,0.54,0.545,0.55,0.555,0.56,0.565,0.57,0.575,0.58,0.585,0.59,0.595,0.6,0.605,0.61,0.615,0.62,0.625,0.63,0.635,0.64,0.645,0.65,0.655,0.66,0.665,0.67,0.675,0.68,0.685,0.69,0.695,0.7,0.705,0.71,0.715,0.72,0.725,0.73,0.735,0.74,0.745,0.75,0.755,0.76,0.765,0.77,0.775,0.78,0.785,0.79,0.795,0.8,0.805,0.81,0.815,0.82,0.825,0.83,0.835,0.84,0.845,0.85,0.855,0.86,0.865,0.87,0.875,0.88,0.885,0.89,0.895,0.9,0.905,0.91,0.915,0.92,0.925,0.93,0.935,0.94,0.945,0.95,0.955,0.96,0.965,0.97,0.975,0.98,0.985,0.99,0.995,1,1.005,1.01,1.015,1.02,1.025,1.03,1.035,1.04,1.045,1.05,1.055,1.06,1.065,1.07,1.075,1.08,1.085,1.09,1.095,1.1,1.105,1.11,1.115,1.12,1.125,1.13,1.135,1.14,1.145,1.15,1.155,1.16,1.165,1.17,1.175,1.18,1.185,1.19,1.195,1.2,1.205,1.21,1.215,1.22,1.225,1.23,1.235,1.24,1.245,1.25,1.255,1.26,1.265,1.27,1.275,1.28,1.285,1.29,1.295,1.3,1.305,1.31,1.315,1.32,1.325,1.33,1.335,1.34,1.345,1.35,1.355,1.36,1.365,1.37,1.375,1.38,1.385,1.39,1.395,1.4,1.405,1.41,1.415,1.42,1.425,1.43,1.435,1.44,1.445,1.45,1.455,1.46,1.465,1.47,1.475,1.48,1.485,1.49,1.495,1.5,1.505,1.51,1.515,1.52,1.525,1.53,1.535,1.54,1.545,1.55,1.555,1.56,1.565,1.57,1.575,1.58,1.585,1.59,1.595,1.6,1.605,1.61,1.615,1.62,1.625,1.63,1.635,1.64,1.645,1.65,1.655,1.66,1.665,1.67,1.675,1.68,1.685,1.69,1.695,1.7,1.705,1.71,1.715,1.72,1.725,1.73,1.735,1.74,1.745,1.75,1.755,1.76,1.765,1.77,1.775,1.78,1.785,1.79,1.795,1.8,1.805,1.81,1.815,1.82,1.825,1.83,1.835,1.84,1.845,1.85,1.855,1.86,1.865,1.87,1.875,1.88,1.885,1.89,1.895,1.9,1.905,1.91,1.915,1.92,1.925,1.93,1.935,1.94,1.945,1.95,1.955,1.96,1.965,1.97,1.975,1.98,1.985,1.99,1.995,2,2.005,2.01,2.015,2.02,2.025,2.03,2.035,2.04,2.045,2.05,2.055,2.06,2.065,2.07,2.075,2.08,2.085,2.09,2.095,2.1,2.105,2.11,2.115,2.12,2.125,2.13,2.135,2.14,2.145,2.15,2.155,2.16,2.165,2.17,2.175,2.18,2.185,2.19,2.195,2.2,2.205,2.21,2.215,2.22,2.225,2.23,2.235,2.24,2.245,2.25,2.255,2.26,2.265,2.27,2.275,2.28,2.285,2.29,2.295,2.3,2.305,2.31,2.315,2.32,2.325,2.33,2.335,2.34,2.345,2.35,2.355,2.36,2.365,2.37,2.375,2.38,2.385,2.39,2.395,2.4,2.405,2.41,2.415,2.42,2.425,2.43,2.435,2.44,2.445,2.45,2.455,2.46,2.465,2.47,2.475,2.48,2.485,2.49,2.495,2.5,2.505,2.51,2.515,2.52,2.525,2.53,2.535,2.54,2.545,2.55,2.555,2.56,2.565,2.57,2.575,2.58,2.585,2.59,2.595,2.6,2.605,2.61,2.615,2.62,2.625,2.63,2.635,2.64,2.645,2.65,2.655,2.66,2.665,2.67,2.675,2.68,2.685,2.69,2.695,2.7,2.705,2.71,2.715,2.72,2.725,2.73,2.735,2.74,2.745,2.75,2.755,2.76,2.765,2.77,2.775,2.78,2.785,2.79,2.795,2.8,2.805,2.81,2.815,2.82,2.825,2.83,2.835,2.84,2.845,2.85,2.855,2.86,2.865,2.87,2.875,2.88,2.885,2.89,2.895,2.9,2.905,2.91,2.915,2.92,2.925,2.93,2.935,2.94,2.945,2.95,2.955,2.96,2.965,2.97,2.975,2.98,2.985,2.99,2.995,3,3.005,3.01,3.015,3.02,3.025,3.03,3.035,3.04,3.045,3.05,3.055,3.06,3.065,3.07,3.075,3.08,3.085,3.09,3.095,3.1,3.105,3.11,3.115,3.12,3.125,3.13,3.135,3.14,3.145,3.15,3.155,3.16,3.165,3.17,3.175,3.18,3.185,3.19,3.195,3.2,3.205,3.21,3.215,3.22,3.225,3.23,3.235,3.24,3.245,3.25,3.255,3.26,3.265,3.27,3.275,3.28,3.285,3.29,3.295,3.3,3.305,3.31,3.315,3.32,3.325,3.33,3.335,3.34,3.345,3.35,3.355,3.36,3.365,3.37,3.375,3.38,3.385,3.39,3.395,3.4,3.405,3.41,3.415,3.42,3.425,3.43,3.435,3.44,3.445,3.45,3.455,3.46,3.465,3.47,3.475,3.48,3.485,3.49,3.495,3.5,3.505,3.51,3.515,3.52,3.525,3.53,3.535,3.54,3.545,3.55,3.555,3.56,3.565,3.57,3.575,3.58,3.585,3.59,3.595,3.6,3.605,3.61,3.615,3.62,3.625,3.63,3.635,3.64,3.645,3.65,3.655,3.66,3.665,3.67,3.675,3.68,3.685,3.69,3.695,3.7,3.705,3.71,3.715,3.72,3.725,3.73,3.735,3.74,3.745,3.75,3.755,3.76,3.765,3.77,3.775,3.78,3.785,3.79,3.795,3.8,3.805,3.81,3.815,3.82,3.825,3.83,3.835,3.84,3.845,3.85,3.855,3.86,3.865,3.87,3.875,3.88,3.885,3.89,3.895,3.9,3.905,3.91,3.915,3.92,3.925,3.93,3.935,3.94,3.945,3.95,3.955,3.96,3.965,3.97,3.975,3.98,3.985,3.99,3.995,4,4.005,4.01,4.015,4.02,4.025,4.03,4.035,4.04,4.045,4.05,4.055,4.06,4.065,4.07,4.075,4.08,4.085,4.09,4.095,4.1,4.105,4.11,4.115,4.12,4.125,4.13,4.135,4.14,4.145,4.15,4.155,4.16,4.165,4.17,4.175,4.18,4.185,4.19,4.195,4.2,4.205,4.21,4.215,4.22,4.225,4.23,4.235,4.24,4.245,4.25,4.255,4.26,4.265,4.27,4.275,4.28,4.285,4.29,4.295,4.3,4.305,4.31,4.315,4.32,4.325,4.33,4.335,4.34,4.345,4.35,4.355,4.36,4.365,4.37,4.375,4.38,4.385,4.39,4.395,4.4,4.405,4.41,4.415,4.42,4.425,4.43,4.435,4.44,4.445,4.45,4.455,4.46,4.465,4.47,4.475,4.48,4.485,4.49,4.495,4.5,4.505,4.51,4.515,4.52,4.525,4.53,4.535,4.54,4.545,4.55,4.555,4.56,4.565,4.57,4.575,4.58,4.585,4.59,4.595,4.6,4.605,4.61,4.615,4.62,4.625,4.63,4.635,4.64,4.645,4.65,4.655,4.66,4.665,4.67,4.675,4.68,4.685,4.69,4.695,4.7,4.705,4.71,4.715,4.72,4.725,4.73,4.735,4.74,4.745,4.75,4.755,4.76,4.765,4.77,4.775,4.78,4.785,4.79,4.795,4.8,4.805,4.81,4.815,4.82,4.825,4.83,4.835,4.84,4.845,4.85,4.855,4.86,4.865,4.87,4.875,4.88,4.885,4.89,4.895,4.9,4.905,4.91,4.915,4.92,4.925,4.93,4.935,4.94,4.945,4.95,4.955,4.96,4.965,4.97,4.975,4.98,4.985,4.99,4.995,0,0.005,0.01,0.015,0.02,0.025,0.03,0.035,0.04,0.045,0.05,0.055,0.06,0.065,0.07,0.075,0.08,0.085,0.09,0.095,0.1,0.105,0.11,0.115,0.12,0.125,0.13,0.135,0.14,0.145,0.15,0.155,0.16,0.165,0.17,0.175,0.18,0.185,0.19,0.195,0.2,0.205,0.21,0.215,0.22,0.225,0.23,0.235,0.24,0.245,0.25,0.255,0.26,0.265,0.27,0.275,0.28,0.285,0.29,0.295,0.3,0.305,0.31,0.315,0.32,0.325,0.33,0.335,0.34,0.345,0.35,0.355,0.36,0.365,0.37,0.375,0.38,0.385,0.39,0.395,0.4,0.405,0.41,0.415,0.42,0.425,0.43,0.435,0.44,0.445,0.45,0.455,0.46,0.465,0.47,0.475,0.48,0.485,0.49,0.495,0.5,0.505,0.51,0.515,0.52,0.525,0.53,0.535,0.54,0.545,0.55,0.555,0.56,0.565,0.57,0.575,0.58,0.585,0.59,0.595,0.6,0.605,0.61,0.615,0.62,0.625,0.63,0.635,0.64,0.645,0.65,0.655,0.66,0.665,0.67,0.675,0.68,0.685,0.69,0.695,0.7,0.705,0.71,0.715,0.72,0.725,0.73,0.735,0.74,0.745,0.75,0.755,0.76,0.765,0.77,0.775,0.78,0.785,0.79,0.795,0.8,0.805,0.81,0.815,0.82,0.825,0.83,0.835,0.84,0.845,0.85,0.855,0.86,0.865,0.87,0.875,0.88,0.885,0.89,0.895,0.9,0.905,0.91,0.915,0.92,0.925,0.93,0.935,0.94,0.945,0.95,0.955,0.96,0.965,0.97,0.975,0.98,0.985,0.99,0.995,1,1.005,1.01,1.015,1.02,1.025,1.03,1.035,1.04,1.045,1.05,1.055,1.06,1.065,1.07,1.075,1.08,1.085,1.09,1.095,1.1,1.105,1.11,1.115,1.12,1.125,1.13,1.135,1.14,1.145,1.15,1.155,1.16,1.165,1.17,1.175,1.18,1.185,1.19,1.195,1.2,1.205,1.21,1.215,1.22,1.225,1.23,1.235,1.24,1.245,1.25,1.255,1.26,1.265,1.27,1.275,1.28,1.285,1.29,1.295,1.3,1.305,1.31,1.315,1.32,1.325,1.33,1.335,1.34,1.345,1.35,1.355,1.36,1.365,1.37,1.375,1.38,1.385,1.39,1.395,1.4,1.405,1.41,1.415,1.42,1.425,1.43,1.435,1.44,1.445,1.45,1.455,1.46,1.465,1.47,1.475,1.48,1.485,1.49,1.495,1.5,1.505,1.51,1.515,1.52,1.525,1.53,1.535,1.54,1.545,1.55,1.555,1.56,1.565,1.57,1.575,1.58,1.585,1.59,1.595,1.6,1.605,1.61,1.615,1.62,1.625,1.63,1.635,1.64,1.645,1.65,1.655,1.66,1.665,1.67,1.675,1.68,1.685,1.69,1.695,1.7,1.705,1.71,1.715,1.72,1.725,1.73,1.735,1.74,1.745,1.75,1.755,1.76,1.765,1.77,1.775,1.78,1.785,1.79,1.795,1.8,1.805,1.81,1.815,1.82,1.825,1.83,1.835,1.84,1.845,1.85,1.855,1.86,1.865,1.87,1.875,1.88,1.885,1.89,1.895,1.9,1.905,1.91,1.915,1.92,1.925,1.93,1.935,1.94,1.945,1.95,1.955,1.96,1.965,1.97,1.975,1.98,1.985,1.99,1.995,2,2.005,2.01,2.015,2.02,2.025,2.03,2.035,2.04,2.045,2.05,2.055,2.06,2.065,2.07,2.075,2.08,2.085,2.09,2.095,2.1,2.105,2.11,2.115,2.12,2.125,2.13,2.135,2.14,2.145,2.15,2.155,2.16,2.165,2.17,2.175,2.18,2.185,2.19,2.195,2.2,2.205,2.21,2.215,2.22,2.225,2.23,2.235,2.24,2.245,2.25,2.255,2.26,2.265,2.27,2.275,2.28,2.285,2.29,2.295,2.3,2.305,2.31,2.315,2.32,2.325,2.33,2.335,2.34,2.345,2.35,2.355,2.36,2.365,2.37,2.375,2.38,2.385,2.39,2.395,2.4,2.405,2.41,2.415,2.42,2.425,2.43,2.435,2.44,2.445,2.45,2.455,2.46,2.465,2.47,2.475,2.48,2.485,2.49,2.495,2.5,2.505,2.51,2.515,2.52,2.525,2.53,2.535,2.54,2.545,2.55,2.555,2.56,2.565,2.57,2.575,2.58,2.585,2.59,2.595,2.6,2.605,2.61,2.615,2.62,2.625,2.63,2.635,2.64,2.645,2.65,2.655,2.66,2.665,2.67,2.675,2.68,2.685,2.69,2.695,2.7,2.705,2.71,2.715,2.72,2.725,2.73,2.735,2.74,2.745,2.75,2.755,2.76,2.765,2.77,2.775,2.78,2.785,2.79,2.795,2.8,2.805,2.81,2.815,2.82,2.825,2.83,2.835,2.84,2.845,2.85,2.855,2.86,2.865,2.87,2.875,2.88,2.885,2.89,2.895,2.9,2.905,2.91,2.915,2.92,2.925,2.93,2.935,2.94,2.945,2.95,2.955,2.96,2.965,2.97,2.975,2.98,2.985,2.99,2.995,3,3.005,3.01,3.015,3.02,3.025,3.03,3.035,3.04,3.045,3.05,3.055,3.06,3.065,3.07,3.075,3.08,3.085,3.09,3.095,3.1,3.105,3.11,3.115,3.12,3.125,3.13,3.135,3.14,3.145,3.15,3.155,3.16,3.165,3.17,3.175,3.18,3.185,3.19,3.195,3.2,3.205,3.21,3.215,3.22,3.225,3.23,3.235,3.24,3.245,3.25,3.255,3.26,3.265,3.27,3.275,3.28,3.285,3.29,3.295,3.3,3.305,3.31,3.315,3.32,3.325,3.33,3.335,3.34,3.345,3.35,3.355,3.36,3.365,3.37,3.375,3.38,3.385,3.39,3.395,3.4,3.405,3.41,3.415,3.42,3.425,3.43,3.435,3.44,3.445,3.45,3.455,3.46,3.465,3.47,3.475,3.48,3.485,3.49,3.495,3.5,3.505,3.51,3.515,3.52,3.525,3.53,3.535,3.54,3.545,3.55,3.555,3.56,3.565,3.57,3.575,3.58,3.585,3.59,3.595,3.6,3.605,3.61,3.615,3.62,3.625,3.63,3.635,3.64,3.645,3.65,3.655,3.66,3.665,3.67,3.675,3.68,3.685,3.69,3.695,3.7,3.705,3.71,3.715,3.72,3.725,3.73,3.735,3.74,3.745,3.75,3.755,3.76,3.765,3.77,3.775,3.78,3.785,3.79,3.795,3.8,3.805,3.81,3.815,3.82,3.825,3.83,3.835,3.84,3.845,3.85,3.855,3.86,3.865,3.87,3.875,3.88,3.885,3.89,3.895,3.9,3.905,3.91,3.915,3.92,3.925,3.93,3.935,3.94,3.945,3.95,3.955,3.96,3.965,3.97,3.975,3.98,3.985,3.99,3.995,4,4.005,4.01,4.015,4.02,4.025,4.03,4.035,4.04,4.045,4.05,4.055,4.06,4.065,4.07,4.075,4.08,4.085,4.09,4.095,4.1,4.105,4.11,4.115,4.12,4.125,4.13,4.135,4.14,4.145,4.15,4.155,4.16,4.165,4.17,4.175,4.18,4.185,4.19,4.195,4.2,4.205,4.21,4.215,4.22,4.225,4.23,4.235,4.24,4.245,4.25,4.255,4.26,4.265,4.27,4.275,4.28,4.285,4.29,4.295,4.3,4.305,4.31,4.315,4.32,4.325,4.33,4.335,4.34,4.345,4.35,4.355,4.36,4.365,4.37,4.375,4.38,4.385,4.39,4.395,4.4,4.405,4.41,4.415,4.42,4.425,4.43,4.435,4.44,4.445,4.45,4.455,4.46,4.465,4.47,4.475,4.48,4.485,4.49,4.495,4.5,4.505,4.51,4.515,4.52,4.525,4.53,4.535,4.54,4.545,4.55,4.555,4.56,4.565,4.57,4.575,4.58,4.585,4.59,4.595,4.6,4.605,4.61,4.615,4.62,4.625,4.63,4.635,4.64,4.645,4.65,4.655,4.66,4.665,4.67,4.675,4.68,4.685,4.69,4.695,4.7,4.705,4.71,4.715,4.72,4.725,4.73,4.735,4.74,4.745,4.75,4.755,4.76,4.765,4.77,4.775,4.78,4.785,4.79,4.795,4.8,4.805,4.81,4.815,4.82,4.825,4.83,4.835,4.84,4.845,4.85,4.855,4.86,4.865,4.87,4.875,4.88,4.885,4.89,4.895,4.9,4.905,4.91,4.915,4.92,4.925,4.93,4.935,4.94,4.945,4.95,4.955,4.96,4.965,4.97,4.975,4.98,4.985,4.99,4.995,0,0.005,0.01,0.015,0.02,0.025,0.03,0.035,0.04,0.045,0.05,0.055,0.06,0.065,0.07,0.075,0.08,0.085,0.09,0.095,0.1,0.105,0.11,0.115,0.12,0.125,0.13,0.135,0.14,0.145,0.15,0.155,0.16,0.165,0.17,0.175,0.18,0.185,0.19,0.195,0.2,0.205,0.21,0.215,0.22,0.225,0.23,0.235,0.24,0.245,0.25,0.255,0.26,0.265,0.27,0.275,0.28,0.285,0.29,0.295,0.3,0.305,0.31,0.315,0.32,0.325,0.33,0.335,0.34,0.345,0.35,0.355,0.36,0.365,0.37,0.375,0.38,0.385,0.39,0.395,0.4,0.405,0.41,0.415,0.42,0.425,0.43,0.435,0.44,0.445,0.45,0.455,0.46,0.465,0.47,0.475,0.48,0.485,0.49,0.495,0.5,0.505,0.51,0.515,0.52,0.525,0.53,0.535,0.54,0.545,0.55,0.555,0.56,0.565,0.57,0.575,0.58,0.585,0.59,0.595,0.6,0.605,0.61,0.615,0.62,0.625,0.63,0.635,0.64,0.645,0.65,0.655,0.66,0.665,0.67,0.675,0.68,0.685,0.69,0.695,0.7,0.705,0.71,0.715,0.72,0.725,0.73,0.735,0.74,0.745,0.75,0.755,0.76,0.765,0.77,0.775,0.78,0.785,0.79,0.795,0.8,0.805,0.81,0.815,0.82,0.825,0.83,0.835,0.84,0.845,0.85,0.855,0.86,0.865,0.87,0.875,0.88,0.885,0.89,0.895,0.9,0.905,0.91,0.915,0.92,0.925,0.93,0.935,0.94,0.945,0.95,0.955,0.96,0.965,0.97,0.975,0.98,0.985,0.99,0.995,1,1.005,1.01,1.015,1.02,1.025,1.03,1.035,1.04,1.045,1.05,1.055,1.06,1.065,1.07,1.075,1.08,1.085,1.09,1.095,1.1,1.105,1.11,1.115,1.12,1.125,1.13,1.135,1.14,1.145,1.15,1.155,1.16,1.165,1.17,1.175,1.18,1.185,1.19,1.195,1.2,1.205,1.21,1.215,1.22,1.225,1.23,1.235,1.24,1.245,1.25,1.255,1.26,1.265,1.27,1.275,1.28,1.285,1.29,1.295,1.3,1.305,1.31,1.315,1.32,1.325,1.33,1.335,1.34,1.345,1.35,1.355,1.36,1.365,1.37,1.375,1.38,1.385,1.39,1.395,1.4,1.405,1.41,1.415,1.42,1.425,1.43,1.435,1.44,1.445,1.45,1.455,1.46,1.465,1.47,1.475,1.48,1.485,1.49,1.495,1.5,1.505,1.51,1.515,1.52,1.525,1.53,1.535,1.54,1.545,1.55,1.555,1.56,1.565,1.57,1.575,1.58,1.585,1.59,1.595,1.6,1.605,1.61,1.615,1.62,1.625,1.63,1.635,1.64,1.645,1.65,1.655,1.66,1.665,1.67,1.675,1.68,1.685,1.69,1.695,1.7,1.705,1.71,1.715,1.72,1.725,1.73,1.735,1.74,1.745,1.75,1.755,1.76,1.765,1.77,1.775,1.78,1.785,1.79,1.795,1.8,1.805,1.81,1.815,1.82,1.825,1.83,1.835,1.84,1.845,1.85,1.855,1.86,1.865,1.87,1.875,1.88,1.885,1.89,1.895,1.9,1.905,1.91,1.915,1.92,1.925,1.93,1.935,1.94,1.945,1.95,1.955,1.96,1.965,1.97,1.975,1.98,1.985,1.99,1.995,2,2.005,2.01,2.015,2.02,2.025,2.03,2.035,2.04,2.045,2.05,2.055,2.06,2.065,2.07,2.075,2.08,2.085,2.09,2.095,2.1,2.105,2.11,2.115,2.12,2.125,2.13,2.135,2.14,2.145,2.15,2.155,2.16,2.165,2.17,2.175,2.18,2.185,2.19,2.195,2.2,2.205,2.21,2.215,2.22,2.225,2.23,2.235,2.24,2.245,2.25,2.255,2.26,2.265,2.27,2.275,2.28,2.285,2.29,2.295,2.3,2.305,2.31,2.315,2.32,2.325,2.33,2.335,2.34,2.345,2.35,2.355,2.36,2.365,2.37,2.375,2.38,2.385,2.39,2.395,2.4,2.405,2.41,2.415,2.42,2.425,2.43,2.435,2.44,2.445,2.45,2.455,2.46,2.465,2.47,2.475,2.48,2.485,2.49,2.495,2.5,2.505,2.51,2.515,2.52,2.525,2.53,2.535,2.54,2.545,2.55,2.555,2.56,2.565,2.57,2.575,2.58,2.585,2.59,2.595,2.6,2.605,2.61,2.615,2.62,2.625,2.63,2.635,2.64,2.645,2.65,2.655,2.66,2.665,2.67,2.675,2.68,2.685,2.69,2.695,2.7,2.705,2.71,2.715,2.72,2.725,2.73,2.735,2.74,2.745,2.75,2.755,2.76,2.765,2.77,2.775,2.78,2.785,2.79,2.795,2.8,2.805,2.81,2.815,2.82,2.825,2.83,2.835,2.84,2.845,2.85,2.855,2.86,2.865,2.87,2.875,2.88,2.885,2.89,2.895,2.9,2.905,2.91,2.915,2.92,2.925,2.93,2.935,2.94,2.945,2.95,2.955,2.96,2.965,2.97,2.975,2.98,2.985,2.99,2.995,3,3.005,3.01,3.015,3.02,3.025,3.03,3.035,3.04,3.045,3.05,3.055,3.06,3.065,3.07,3.075,3.08,3.085,3.09,3.095,3.1,3.105,3.11,3.115,3.12,3.125,3.13,3.135,3.14,3.145,3.15,3.155,3.16,3.165,3.17,3.175,3.18,3.185,3.19,3.195,3.2,3.205,3.21,3.215,3.22,3.225,3.23,3.235,3.24,3.245,3.25,3.255,3.26,3.265,3.27,3.275,3.28,3.285,3.29,3.295,3.3,3.305,3.31,3.315,3.32,3.325,3.33,3.335,3.34,3.345,3.35,3.355,3.36,3.365,3.37,3.375,3.38,3.385,3.39,3.395,3.4,3.405,3.41,3.415,3.42,3.425,3.43,3.435,3.44,3.445,3.45,3.455,3.46,3.465,3.47,3.475,3.48,3.485,3.49,3.495,3.5,3.505,3.51,3.515,3.52,3.525,3.53,3.535,3.54,3.545,3.55,3.555,3.56,3.565,3.57,3.575,3.58,3.585,3.59,3.595,3.6,3.605,3.61,3.615,3.62,3.625,3.63,3.635,3.64,3.645,3.65,3.655,3.66,3.665,3.67,3.675,3.68,3.685,3.69,3.695,3.7,3.705,3.71,3.715,3.72,3.725,3.73,3.735,3.74,3.745,3.75,3.755,3.76,3.765,3.77,3.775,3.78,3.785,3.79,3.795,3.8,3.805,3.81,3.815,3.82,3.825,3.83,3.835,3.84,3.845,3.85,3.855,3.86,3.865,3.87,3.875,3.88,3.885,3.89,3.895,3.9,3.905,3.91,3.915,3.92,3.925,3.93,3.935,3.94,3.945,3.95,3.955,3.96,3.965,3.97,3.975,3.98,3.985,3.99,3.995,4,4.005,4.01,4.015,4.02,4.025,4.03,4.035,4.04,4.045,4.05,4.055,4.06,4.065,4.07,4.075,4.08,4.085,4.09,4.095,4.1,4.105,4.11,4.115,4.12,4.125,4.13,4.135,4.14,4.145,4.15,4.155,4.16,4.165,4.17,4.175,4.18,4.185,4.19,4.195,4.2,4.205,4.21,4.215,4.22,4.225,4.23,4.235,4.24,4.245,4.25,4.255,4.26,4.265,4.27,4.275,4.28,4.285,4.29,4.295,4.3,4.305,4.31,4.315,4.32,4.325,4.33,4.335,4.34,4.345,4.35,4.355,4.36,4.365,4.37,4.375,4.38,4.385,4.39,4.395,4.4,4.405,4.41,4.415,4.42,4.425,4.43,4.435,4.44,4.445,4.45,4.455,4.46,4.465,4.47,4.475,4.48,4.485,4.49,4.495,4.5,4.505,4.51,4.515,4.52,4.525,4.53,4.535,4.54,4.545,4.55,4.555,4.56,4.565,4.57,4.575,4.58,4.585,4.59,4.595,4.6,4.605,4.61,4.615,4.62,4.625,4.63,4.635,4.64,4.645,4.65,4.655,4.66,4.665,4.67,4.675,4.68,4.685,4.69,4.695,4.7,4.705,4.71,4.715,4.72,4.725,4.73,4.735,4.74,4.745,4.75,4.755,4.76,4.765,4.77,4.775,4.78,4.785,4.79,4.795,4.8,4.805,4.81,4.815,4.82,4.825,4.83,4.835,4.84,4.845,4.85,4.855,4.86,4.865,4.87,4.875,4.88,4.885,4.89,4.895,4.9,4.905,4.91,4.915,4.92,4.925,4.93,4.935,4.94,4.945,4.95,4.955,4.96,4.965,4.97,4.975,4.98,4.985,4.99,4.995]}";
				String pattern = "\"data\":.*]";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(result);
				m.find();
				String data = m.group().toString().replace("\"data\":", "");
				System.out.println(data);

				Thread.sleep(1 * 1000);

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
