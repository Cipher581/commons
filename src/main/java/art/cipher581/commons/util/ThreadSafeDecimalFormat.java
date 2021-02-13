package art.cipher581.commons.util;


import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Currency;


public class ThreadSafeDecimalFormat extends DecimalFormat {

	/**
	 * SVUID
	 */
	private static final long serialVersionUID = -1286783112824842982L;


	public ThreadSafeDecimalFormat() {
		super();
	}


	public ThreadSafeDecimalFormat(String pattern, DecimalFormatSymbols symbols) {
		super(pattern, symbols);
	}


	public ThreadSafeDecimalFormat(String pattern) {
		super(pattern);
	}


	@Override
	public synchronized StringBuffer format(double number, StringBuffer result, FieldPosition fieldPosition) {
		return super.format(number, result, fieldPosition);
	}


	@Override
	public synchronized StringBuffer format(long number, StringBuffer result, FieldPosition fieldPosition) {
		return super.format(number, result, fieldPosition);
	}


	@Override
	public synchronized AttributedCharacterIterator formatToCharacterIterator(Object obj) {
		return super.formatToCharacterIterator(obj);
	}


	@Override
	public synchronized Number parse(String text, ParsePosition pos) {
		return super.parse(text, pos);
	}


	@Override
	public synchronized DecimalFormatSymbols getDecimalFormatSymbols() {
		return super.getDecimalFormatSymbols();
	}


	@Override
	public synchronized void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) {
		super.setDecimalFormatSymbols(newSymbols);
	}


	@Override
	public synchronized String getPositivePrefix() {
		return super.getPositivePrefix();
	}


	@Override
	public synchronized void setPositivePrefix(String newValue) {
		super.setPositivePrefix(newValue);
	}


	@Override
	public synchronized String getNegativePrefix() {
		return super.getNegativePrefix();
	}


	@Override
	public synchronized void setNegativePrefix(String newValue) {
		super.setNegativePrefix(newValue);
	}


	@Override
	public synchronized String getPositiveSuffix() {
		return super.getPositiveSuffix();
	}


	@Override
	public synchronized void setPositiveSuffix(String newValue) {
		super.setPositiveSuffix(newValue);
	}


	@Override
	public synchronized String getNegativeSuffix() {
		return super.getNegativeSuffix();
	}


	@Override
	public synchronized void setNegativeSuffix(String newValue) {
		super.setNegativeSuffix(newValue);
	}


	@Override
	public synchronized int getMultiplier() {
		return super.getMultiplier();
	}


	@Override
	public synchronized void setMultiplier(int newValue) {
		super.setMultiplier(newValue);
	}


	@Override
	public synchronized int getGroupingSize() {
		return super.getGroupingSize();
	}


	@Override
	public synchronized void setGroupingSize(int newValue) {
		super.setGroupingSize(newValue);
	}


	@Override
	public synchronized boolean isDecimalSeparatorAlwaysShown() {
		return super.isDecimalSeparatorAlwaysShown();
	}


	@Override
	public synchronized void setDecimalSeparatorAlwaysShown(boolean newValue) {
		super.setDecimalSeparatorAlwaysShown(newValue);
	}


	@Override
	public synchronized boolean isParseBigDecimal() {
		return super.isParseBigDecimal();
	}


	@Override
	public synchronized void setParseBigDecimal(boolean newValue) {
		super.setParseBigDecimal(newValue);
	}


	@Override
	public synchronized Object clone() {
		return super.clone();
	}


	@Override
	public synchronized boolean equals(Object obj) {
		return super.equals(obj);
	}


	@Override
	public synchronized int hashCode() {
		return super.hashCode();
	}


	@Override
	public synchronized String toPattern() {
		return super.toPattern();
	}


	@Override
	public synchronized String toLocalizedPattern() {
		return super.toLocalizedPattern();
	}


	@Override
	public synchronized void applyPattern(String pattern) {
		super.applyPattern(pattern);
	}


	@Override
	public synchronized void applyLocalizedPattern(String pattern) {
		super.applyLocalizedPattern(pattern);
	}


	@Override
	public synchronized void setMaximumIntegerDigits(int newValue) {
		super.setMaximumIntegerDigits(newValue);
	}


	@Override
	public synchronized void setMinimumIntegerDigits(int newValue) {
		super.setMinimumIntegerDigits(newValue);
	}


	@Override
	public synchronized void setMaximumFractionDigits(int newValue) {
		super.setMaximumFractionDigits(newValue);
	}


	@Override
	public synchronized void setMinimumFractionDigits(int newValue) {
		super.setMinimumFractionDigits(newValue);
	}


	@Override
	public synchronized int getMaximumIntegerDigits() {
		return super.getMaximumIntegerDigits();
	}


	@Override
	public synchronized int getMinimumIntegerDigits() {
		return super.getMinimumIntegerDigits();
	}


	@Override
	public synchronized int getMaximumFractionDigits() {
		return super.getMaximumFractionDigits();
	}


	@Override
	public synchronized int getMinimumFractionDigits() {
		return super.getMinimumFractionDigits();
	}


	@Override
	public synchronized Currency getCurrency() {
		return super.getCurrency();
	}


	@Override
	public synchronized void setCurrency(Currency currency) {
		super.setCurrency(currency);
	}


	@Override
	public synchronized RoundingMode getRoundingMode() {
		return super.getRoundingMode();
	}


	@Override
	public synchronized void setRoundingMode(RoundingMode roundingMode) {
		super.setRoundingMode(roundingMode);
	}


	@Override
	public synchronized Number parse(String source) throws ParseException {
		return super.parse(source);
	}


	@Override
	public synchronized boolean isParseIntegerOnly() {
		return super.isParseIntegerOnly();
	}


	@Override
	public synchronized void setParseIntegerOnly(boolean value) {
		super.setParseIntegerOnly(value);
	}


	@Override
	public synchronized boolean isGroupingUsed() {
		return super.isGroupingUsed();
	}


	@Override
	public synchronized void setGroupingUsed(boolean newValue) {
		super.setGroupingUsed(newValue);
	}


	@Override
	public synchronized Object parseObject(String source) throws ParseException {
		return super.parseObject(source);
	}

}
