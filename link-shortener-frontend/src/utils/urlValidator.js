const isValidUrl = (url) => {
  const regex = /^(https?:\/\/)?([\w\-])+\.{1}([a-zA-Z]{2,63})([\/\w\-.\?%&=]*)?$/;
  return regex.test(url);
};

export default isValidUrl;
