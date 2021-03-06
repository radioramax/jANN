package de.unikassel.ann.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.collections.CollectionUtils;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.prefs.CsvPreference;

import de.unikassel.ann.config.NetConfig;
import de.unikassel.ann.io.beans.SynapseBean;
import de.unikassel.ann.io.beans.TopologyBean;
import de.unikassel.ann.io.beans.TrainingBean;
import de.unikassel.ann.model.DataPair;
import de.unikassel.ann.model.DataPairSet;

public class NetIO {

	public final static String OPEN_TAG = "#<";
	public final static String CLOSE_TAG = "#>";
	public final static String TOPOLOGY_TAG = "topology";
	public final static String SYNAPSE_TAG = "synapses";
	public final static String TRAINING_TAG = "training";
	public final static String DATASET = "dataset";
	static Logger log = Logger.getAnonymousLogger();

	static final int MAX_BUFFER_SIZE = 1024 * 1024;

	static CsvPreference pref = new CsvPreference('\"', ';', "\r\n");
	static String[] header2beanMapping;
	static CellProcessor[] processor;

	public List<TrainingBean> trainigBeanList = null;
	public List<TopologyBean> topoBeanList = null;
	public List<SynapseBean> synapsesBanList = null;

	public void readTraininData(final InputStream inputStream) throws Exception {

		InputStreamReader inputStremReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStremReader);
		trainigBeanList = TrainingRW.readData(bufferedReader);
		bufferedReader.close();

	}

	public void readConfigFile(final File file) throws Exception {

		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (final FileNotFoundException e) {
			log.warning("could not open file " + file);
			throw e;
		}
		final InputStream fileInputStream = new BufferedInputStream(fis);

		if (fileInputStream.markSupported() == false) {
			log.warning("mark/reset not supported!");
			System.exit(0);
		}
		InputStreamReader inputStremReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStremReader);

		String line;

		while ((line = bufferedReader.readLine()) != null) {
			if (line != null && line.startsWith(OPEN_TAG + TRAINING_TAG)) {
				bufferedReader.mark(MAX_BUFFER_SIZE);
				trainigBeanList = TrainingRW.readData(bufferedReader);
				bufferedReader.reset();
			} else if (line != null && line.startsWith(OPEN_TAG + TOPOLOGY_TAG)) {
				bufferedReader.mark(MAX_BUFFER_SIZE);
				topoBeanList = TopologyBeanRW.readData(bufferedReader);
				bufferedReader.reset();
			} else if (line != null && line.startsWith(OPEN_TAG + SYNAPSE_TAG)) {
				bufferedReader.mark(MAX_BUFFER_SIZE);
				synapsesBanList = SynapseBeanRW.readData(bufferedReader);
				bufferedReader.reset();
			}
		}
		bufferedReader.close();

		if (CollectionUtils.isNotEmpty(topoBeanList)) {
			Collections.sort(topoBeanList);
			int firstId = topoBeanList.get(0).getId();
			if (firstId != 0) {
				throw new IllegalArgumentException("id should start with 0 / zero, but was :" + firstId);
			}

			for (int i = 0; i < topoBeanList.size(); i++) {
				int id = topoBeanList.get(i).getId();
				if (id != i) {
					throw new IllegalArgumentException("ids for neurons must be unique, duplicate id found: " + id);
				}
			}
		}

	}

	public NetConfig generateNetwork() {
		return generateNetwork(true, true, true);
	}

	public NetConfig generateNetwork(final boolean topo, final boolean synapse, final boolean training) {
		if (CollectionUtils.isNotEmpty(topoBeanList)) {

			NetConfig config = new NetConfig();
			if (topo && synapse) {
				config.getNetwork().createTopology(topoBeanList, synapsesBanList);
			} else if (topo && !synapse) {
				config.getNetwork().createTopology(topoBeanList, null);
			}

			if (training) {
				config.setTrainingData(getTrainingSet());
			}
			return config;

		}
		return null;
	}

	public DataPairSet getTrainingSet() {
		if (CollectionUtils.isNotEmpty(trainigBeanList)) {
			DataPairSet set = new DataPairSet();
			for (TrainingBean b : trainigBeanList) {
				DataPair pair = new DataPair(b.getInput(), b.getOutput());
				set.addPair(pair);
			}
			return set;
		}
		return null;
	}

	public void writeDataSet(final File file, final String title, final boolean training, final DataPairSet dataSet) {
		TrainingRW.writeData(dataSet, file, title, training);
	}

	public void writeNet(final File file, final String title, final NetConfig netConfig) {
		TopologyBeanRW.writeData(netConfig, file, title);
		SynapseBeanRW.writeData(netConfig, file);

	}

}
